//
// Created by Sam on 2018/2/13.
//

#include <parser/statement.h>

/**
 * 在现有实现基础上，实现update from子句
 *
 * 支持的update语法：
 *
 * UPDATE <table_name> SET <field1> = <expr1>[, <field2 = <expr2>, ..]
 * WHERE <logical_expr>
 *
 * 解析获得 sql_stmt_update 结构
 */

/*TODO: parse_sql_stmt_update， update语句解析*/
sql_stmt_update *parse_sql_stmt_update(ParserT *parser) {
    //    fprintf(stderr, "TODO: update is not implemented yet. in parse_sql_stmt_update \n");
    // 定义参数存储sqlStmtUpdate字段和相应的value值
    char *tableName = NULL; // 表名
    arraylist *fields = arraylist_create();// 要设值的字段列表
    arraylist *fieldsExpr = arraylist_create();// set表达式值
    SRA_t *whereStmt ;// 构建SRA_t对象 where表达式内容

    /* 1.匹配update关键词*/
    TokenT *token = parseNextToken(parser);
    if(!matchToken(parser, TOKEN_RESERVED_WORD, "update")){
        strcpy(parser->parserMessage, "invalid sql: missing keyword [update].");
        return NULL;
    }
    /* 2.校验表名 */
    token = parseNextToken(parser);
    if (token->type == TOKEN_WORD) {
        // 开辟空间存储表名
        tableName = new_id_name();
        strcpy(tableName, token->text);
    } else {
        strcpy(parser->parserMessage, "invalid sql: missing [table name].");
        return NULL;
    }

    /* 3.匹配set关键词*/
    token = parseEatAndNextToken(parser);
    if(!matchToken(parser, TOKEN_RESERVED_WORD, "set")){
        strcpy(parser->parserMessage, "invalid sql: missing keyword [set].");
        return NULL;
    }

    /* 4.set 表达式校验 */
    token = parseNextToken(parser);
    // nullptr
    if (token != NULL && (token->type == TOKEN_WORD || token->type == TOKEN_COMMA)) {
        while(token != NULL && (token->type == TOKEN_WORD || token->type == TOKEN_COMMA)) {
            if(token->type == TOKEN_COMMA)
            {
                token = parseEatAndNextToken(parser);
            }
            if (token->type == TOKEN_WORD)
            {
                char *fieldName = new_id_name();
                strcpy(fieldName, token->text);
                arraylist_add(fields,fieldName);
            }
            else
            {
                strcpy(parser->parserMessage, "error: file name. \n");
                return NULL;
            }
            parseEatAndNextToken(parser);
            if (matchToken(parser,TOKEN_EQ, "=") == 0)
            {
                strcpy(parser->parserMessage, "error: '=' \n");
                return NULL;
            }
            // 匹配值
            Expression *exp = parseExpressionRD(parser);
            // 将设定值存入set的表达式列表
            arraylist_add(fieldsExpr,exp);
            // 继续校验下一个token
            token = parseNextToken(parser);
        }
    } else {
        strcpy(parser->parserMessage, "invalid sql: missing field name.");
        return NULL;
    }

    /* 5.where表达式解析 */
    whereStmt = SRATable(TableReference_make(tableName, "")); // nullptr
    if(matchToken(parser,TOKEN_RESERVED_WORD, "where")){
        // 解析where子句中的条件表达式
        Expression *whereExpr = parseExpressionRD(parser);
        if (parser->parserStateType == PARSER_WRONG) {
            return NULL;
        }
        whereStmt = SRASelect(whereStmt, whereExpr);
    }



        /* 5.校验where表达式（若存在则校验，不存在则考虑是否要限定为1=1，让物理计划正常执行） */
    /*
        char *ts = new_id_name();
        strcat(ts,"select * from ");
        strcat(ts,tableName);
        // 通过串接where表达式，调用parse_sql_stmt_select获取相应的where解析（select * from tableName where [whereExpr]）
        TokenizerT *tokenizer = TKCreate(ts);
        ParserT *selectParser = newParser(tokenizer);
        memset(selectParser->parserMessage, 0, sizeof(selectParser->parserMessage));

        // 解析 select语句，获得SRA_t对象（需定位到select.sra部分（不包括project））
        whereStmt = parse_sql_stmt_select(selectParser)->select.sra;

        if (whereStmt != NULL) {
            SRA_print(whereStmt);
        } else {
            printf(parser->parserMessage);
        }
*/

    // 返回解析结果
    sql_stmt_update *sqlStmtUpdate = (sql_stmt_update *)calloc(sizeof(sql_stmt_update),1);
    sqlStmtUpdate->tableName = tableName;
    sqlStmtUpdate->fields = fields;
    sqlStmtUpdate->fieldsExpr = fieldsExpr;
    sqlStmtUpdate->where = whereStmt;

    /* 显示update语句包含的查询计划*/
    // sql_stmt_update_print(sqlStmtUpdate);

    return sqlStmtUpdate;
};