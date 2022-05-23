//
// Created by sam on 2018/9/18.
//
#include "physicalplan/physicalplan.h"

/*
 * 执行 update 语句的物理计划，返回修改的记录条数
 * 返回大于等于0的值，表示修改的记录条数；
 * 返回小于0的值，表示修改过程中出现错误。
 *
 * */
/*TODO: plan_execute_update， update语句执行*/
int plan_execute_update(dongmendb *db, sql_stmt_update *sqlStmtUpdate , transaction *tx){

    // 设定记录被更新的条数
    size_t updateCount = 0;
    // 遍及数据表的结果集
    /*
    physical_scan *plan = plan_execute_select(db, sqlStmtUpdate->where, tx);
    arraylist *exprs = plan->physicalScanProject->expr_list;
    printf("\n处理列表数据：");
    while (plan->next(plan)){
        for (int i = 0; i <= exprs->size - 1; i++) {
            variant *var = plan->getValByIndex(plan, i);
            if (var->type == DATA_TYPE_CHAR){
                printf("%s\t", var->strValue);
            }else if(var->type == DATA_TYPE_INT){
                printf("%i\t", var->intValue);
            }
        }
        printf("\n");
    }
    plan->close(plan);
     */

    // update物理计划：创建物理计划并初始化(此处sqlStmtUpdate解析结果若没有限定where条件则获取的scan为null)
    physical_scan *updateScan = physical_scan_generate(db, sqlStmtUpdate->where, tx);
    // 指针置前
    updateScan->beforeFirst(updateScan);
    // 遍历结果集（扫描物理数据表）
    while (updateScan->next(updateScan)){
        for (int i = 0; i <= sqlStmtUpdate->fieldsExpr->size - 1; i++) {
            // 获取字段名称（字段存储在指定的列表中fields）
            char *currentFieldName = arraylist_get(sqlStmtUpdate->fields,i);
            // 为了保存set等号后面的表达式，定义结构体并为其分配存储空间
            variant *val = (variant *)calloc(sizeof(variant),1);
            // 获取要更新的字段的数据类型
            enum data_type currentFieldType = updateScan->getField(updateScan,sqlStmtUpdate->tableName,currentFieldName)->type;

            // 计算update set中针对该字段赋值的表达式
            physical_scan_evaluate_expression(arraylist_get(sqlStmtUpdate->fieldsExpr,i),updateScan,val);

            // 判断字段数据类型是否匹配,如果数据类型不匹配则抛出错误提示
            if(val->type != currentFieldType){
                fprintf(stdout, "field type not match - error\n");
                return DONGMENDB_EINVALIDSQL;
            }

            // 根据现有支持的数据类型进行相应校验操作(int\char)，使用scan设置相应的值（对应表、对应字段、设定值）
            if(val->type==DATA_TYPE_INT){
                // 整型处理
                   updateScan->setInt(updateScan,sqlStmtUpdate->tableName,currentFieldName,val->intValue);
            }else if(val->type==DATA_TYPE_CHAR){
                // 字符串设置
               updateScan->setString(updateScan,sqlStmtUpdate->tableName,currentFieldName,val->strValue);
                // 错误处理：如果设置的字符串长度超出现有字段设定的字符串长度则需要进行额外处理（截取）
            }
        }
        printf("\n");
        // 一次scan的update执行完成，记录数+1
        updateCount++;
    }
    // 关闭scan，返回处理记录条数
    updateScan->close(updateScan);
    return updateCount;
};