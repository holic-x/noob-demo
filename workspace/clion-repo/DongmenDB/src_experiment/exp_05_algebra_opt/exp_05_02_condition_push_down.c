#include <dongmensql/optimizer.h>
/*
 * 使用关于选择的等价变换规则将条件下推。
 * 输入一个关系代数表达式，输出优化后的关系代数表达式
 * 要求：在查询条件符合合取范式的前提下，根据等价变换规则将查询条件移动至合适的位置。
 * */
SRA_t *dongmengdb_algebra_optimize_condition_pushdown(SRA_t *sra, table_manager *tableManager, transaction *tx){




    // 语法树优化处理标识（用于调试）
    bool operFlag = false;

    // 打印语法树
    printf("\n-----------初始化语法树：-----------\n");
    SRA_print(sra);

    if(operFlag){
        /*初始关系代数语法树sra由三个操作构成：SRA_PROJECT -> SRA_SELECT -> SRA_JOIN，即对应语法树中三个节点。*/

        /**
         * 第一步：.等价变换：将SRA_SELECT类型的节点进行条件串接
         * 1.1 在sra中找到每个SRA_Select节点
         * 1.2 检查每个SRA_Select节点中的条件是不是满足串接条件：多条件且用and连接
         * 1.3 若满足串接条件则：创建一组新的串接的SRA_Select节点，等价替换当前的SRA_Select节点
         */
        // 条件串接处理
        condConcatenateHandle(&sra);

        /**
         * 第二步：等价变换：条件交换
         * 2.1 在sra中找到每个SRA_Select节点
         * 2.2 对每个SRA_Select节点做以下处理：
         *  2.2.1 在sra中查找 SRA_Select 节点应该在的最优位置：
         *      若子操作也是SRA_Select，则可交换；
         *      若子操作是笛卡尔积，则可交换，需要判断SRA_Select所包含的属性属于笛卡尔积的哪个子操作
         *  2.2.2 最后将SRA_Select类型的节点移动到语法树的最优位置
         * */
        // 条件交换处理
        condExchangeHandle(&sra, tableManager, tx);

        printf("\n-----------关系代数优化后的语法树：-----------\n");
        SRA_print(sra);
    }
    return sra;
}

Expression *findExpEnd(Expression *pFront,Expression *pBack)
{
    Expression *point = pFront;
    while(point != NULL && point->nextexpr != pBack && point->nextexpr != NULL)
    {
        point = point->nextexpr;
    }
    return  point;
}

/**
 * 查找子表达式
 * @param pExpression
 * @return
 */
Expression *findChildEpr(Expression *pExpression)
{
    if(pExpression == NULL) {
        return pExpression;
    }
    else if(pExpression->term == NULL)
    {
        if(pExpression->opType <= TOKEN_COMMA) {
            int num = 0;
            num = operators[pExpression->opType].numbers;
            pExpression = pExpression->nextexpr;
            while (num--) {
                pExpression = findChildEpr(pExpression);
            }
            return pExpression;
        }else if(pExpression->term)
            return  pExpression->nextexpr;
    }
    return NULL;
}

/**
 * 分割语法树
 * @param pS
 */
void splitSra(SRA_t **pS) {
    if (pS == NULL) {
        return;
    }
    SRA_t *sra = *pS;
    Expression *express = sra->select.cond;
    Expression *operandListFront = express->nextexpr;
    Expression *operandListBack = findChildEpr(operandListFront);
    Expression *frontEnd = findExpEnd(operandListFront, operandListBack);
    frontEnd->nextexpr = NULL;
    sra->select.sra = SRASelect(sra->select.sra, operandListFront);
    sra->select.cond = operandListBack;
}

/**
 * 校验表达式：如果为null返回false，如果不为null则校验opType是否为and关键字
 * @param pExpression
 * @return
 */
bool validExp(Expression *pExpression) {
    return (pExpression == NULL) ? (false) : (pExpression->opType == TOKEN_AND);
}

/**
 * 条件串接处理
 * @param sra_point
 */
void condConcatenateHandle(SRA_t **sra_point) {

    // 获取传入关系代数的指针所指向的数据
    SRA_t *sra_data = *sra_point;
    if (sra_data == NULL) {
        return;
    }
    // 根据关系代数的不同类型进行额外处理
    if (sra_data->t == SRA_SELECT) {
        if (validExp(sra_data->select.cond)) {
            splitSra(sra_point);
        }
        condConcatenateHandle(&(sra_data->select.sra));
    } else if (sra_data->t == SRA_PROJECT) {
        condConcatenateHandle(&(sra_data->project.sra));
    } else if (sra_data->t == SRA_JOIN) {
        condConcatenateHandle(&(sra_data->join.sra1));
        condConcatenateHandle(&(sra_data->join.sra2));
    }
}

/**
 * 列属性校验（如果存在则返回true）
 * 查找指定的columnName是否在fieldsName中，若在则返回true，否则返回false
 * @param fieldsName
 * @param columnName
 * @return
 */
bool hasColumnNameInFieldsName(arraylist *fieldsName, const char *columnName) {
    for(size_t i=0;i<fieldsName->size;i++){
        if (strcmp(arraylist_get(fieldsName,i), columnName) == 0) {
            return true;
        }
    }
    return false;
}

bool hasColumnNameInSra(SRA_t *sra, Expression *pExpression, table_manager *tableManager, transaction *transaction) {
    auto sraType = sra->t;
    if (sraType == SRA_SELECT) {
        return hasColumnNameInSra(sra->select.sra, pExpression, tableManager, transaction);
    } else if (sraType == SRA_JOIN) {
        return (hasColumnNameInSra(sra->join.sra1, pExpression, tableManager, transaction)
                | hasColumnNameInSra(sra->join.sra2, pExpression, tableManager, transaction));
    } else if (sraType == SRA_TABLE) {
        for (Expression *point = pExpression; point != NULL; point = point->nextexpr) {
            if (point->term != NULL && point->term->t == TERM_COLREF) {
                if (point->term->ref->tableName != NULL) {
                    if (strcmp(point->term->ref->tableName,sra->table.ref->table_name) == 0) {
                        return true;
                    }
                } else {
                    table_info *fields = table_manager_get_tableinfo(tableManager,sra->table.ref->table_name, transaction);
                    if (hasColumnNameInFieldsName(fields->fieldsName,point->term->ref->columnName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    return false;
}

/**
 * 条件交换处理
 * @param pS
 * @param tableManager
 * @param transaction
 */
void condExchangeHandle(SRA_t **pS, table_manager *tableManager, transaction *transaction) {
    SRA_t *sra = *pS;
    if (sra == NULL) {
        return;
    } else if (sra->t == SRA_SELECT) {
        condExchangeHandle(&(sra->select.sra), tableManager, transaction);
        SRA_t *selectSra = sra->select.sra;
        if (selectSra->t == SRA_SELECT) {
            sra->select.sra = selectSra->select.sra;
            selectSra->select.sra = sra;
            *pS = selectSra;
            condExchangeHandle(&(selectSra->select.sra), tableManager, transaction);
        } else if (selectSra->t == SRA_JOIN) {
            bool leftBranch = hasColumnNameInSra(selectSra->join.sra1,
                                                 sra->select.cond, tableManager, transaction),
                    rightBranch = hasColumnNameInSra(selectSra->join.sra2,
                                                     sra->select.cond, tableManager, transaction);
            if (leftBranch && !rightBranch) {
                sra->select.sra = selectSra->join.sra1;
                selectSra->join.sra1 = sra;
                *pS = selectSra;
                condExchangeHandle(&(selectSra->join.sra1), tableManager, transaction);
            } else if (!leftBranch && rightBranch) {
                sra->select.sra = selectSra->join.sra2;
                selectSra->join.sra2 = sra;
                *pS = selectSra;
                condExchangeHandle(&(selectSra->join.sra2), tableManager, transaction);
            }
        }
    } else if (sra->t == SRA_PROJECT) {
        condExchangeHandle(&(sra->project.sra), tableManager, transaction);
    } else if (sra->t == SRA_JOIN) {
        condExchangeHandle(&(sra->join.sra1), tableManager, transaction);
        condExchangeHandle(&(sra->join.sra2), tableManager, transaction);
    }
}
