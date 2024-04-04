//
// Created by Sam on 2018/2/13.
//

#include <parser/statement.h>

/**
 * 在现有实现基础上，实现delete from子句
 *
 *  支持的delete语法：
 *
 *  DELETE FROM <table_nbame>
 *  WHERE <logical_expr>
 *
 * 解析获得 sql_stmt_delete 结构
 */

sql_stmt_delete *parse_sql_stmt_delete(ParserT *parser){

    char *tableName = NULL;
    // 构建SRA_t对象
    SRA_t *whereStmt ;

    TokenT *token = parseNextToken(parser);
    /* 1.匹配delete关键词*/
    if(!matchToken(parser, TOKEN_RESERVED_WORD, "delete")){
        printf("delete关键字校验失败提示%s","invalid sql: missing keyword [delete].");
        strcpy(parser->parserMessage, "invalid sql: missing keyword [delete].");
        return NULL;
    }

    /* 2.校验表名 */
    token = parseNextToken(parser);
    if (token->type == TOKEN_WORD) {
        // 开辟空间存储表名
        tableName = new_id_name();
        strcpy(tableName, token->text);
    } else {
        printf("table name校验失败提示%s","invalid sql: missing keyword [table name].");
        strcpy(parser->parserMessage, "invalid sql: missing [table name].");
        return NULL;
    }

    /* 3.校验where表达式 */
    token = parseEatAndNextToken(parser);
    whereStmt = SRATable(TableReference_make(tableName, ""));// nullptr  针对*的情况
    if(matchToken(parser,TOKEN_RESERVED_WORD,"where")){
        // whereStmt = SRATable(TableReference_make(tableName, ""));
        // 获取表达式并包裹在SRA_t中
        Expression *whereExpr = parseExpressionRD(parser);
        if (parser->parserStateType == PARSER_WRONG) {
            return NULL;
        }
        whereStmt = SRASelect(whereStmt, whereExpr);
    }
    /*else{
        // 采用另一种思路单独考虑select * 的情况（等价于whereStmt = SRATable(TableReference_make(tableName, ""));设置）
        // 通过拼接select *的sql语句，调用parse_sql_stmt_select方法获取到其下的sra
        char *ts = new_id_name();
        strcat(ts,"select * from ");
        strcat(ts,tableName);
        TokenizerT *tokenizer = TKCreate(ts);
        ParserT *selectParser = newParser(tokenizer);
        memset(selectParser->parserMessage, 0, sizeof(selectParser->parserMessage));

        // 解析 select语句，获得SRA_t对象（需定位到select.sra部分（不包括project））
        whereStmt = parse_sql_stmt_select(selectParser)->select.sra;
        if (whereStmt != NULL) {
            SRA_print(whereStmt);
        } else {
            printf(selectParser->parserMessage);
        }
    }
     */

    // 返回解析结果
     sql_stmt_delete *sqlStmtDelete = (sql_stmt_delete *)calloc(sizeof(sql_stmt_delete),1);
//    sql_stmt_delete *sqlStmtDelete = (sql_stmt_delete *)calloc(1,sizeof(sql_stmt_delete));
    sqlStmtDelete->tableName = tableName;
    sqlStmtDelete->where = whereStmt;

    /*  打印delete语句解析信息 */
    printf("操作表名：%s\n",sqlStmtDelete->tableName);

    return sqlStmtDelete;
};