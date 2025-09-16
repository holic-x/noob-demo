# Springboot-demo 基础项目示例
- 用于快速开发支持基础数据的CRUD操作
- 不依赖于外部环境（例如外部数据库、Redis等）
- 使用内存中的H2数据库和Spring Data JPA实现数据的持久化操作
- 包括基础开发的一些常用工具（lombok、devtools、test、hutools、json相关等）

# 版本说明
- 项目环境：Springboot + 3.X + JDK 17
  - 基于Springboot-3.X版本，需要配合JDK17使用，如果项目加载失败需要检查版本依赖
- 常见问题是引入依赖后maven长时间加载，考虑可能是因为版本兼容性问题导致，需要检查项目版本依赖、JDK版本环境