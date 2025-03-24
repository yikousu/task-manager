# 任务管理系统

一个简单高效的任务管理系统，基于Spring Boot和Vue.js构建。

## 功能特点

- 添加任务：用户可以输入任务标题、描述、优先级和截止日期
- 标记任务为完成：用户可以标记任务为已完成状态
- 删除任务：支持单个任务删除
- 查看任务列表：显示所有任务，包括名称、描述、优先级、截止日期和状态
- 任务排序：支持按优先级（高→中→低）或截止日期（早→晚）排序

## 技术栈

- 后端：Spring Boot 2.7.18 + MyBatis-Plus 3.5.5
- 前端：Vue 3 + Bootstrap 5
- 数据库：MySQL 8.0

## 快速开始

### 数据库配置

1. 创建名为`my_info`的数据库
2. 执行`src/main/resources/db/init.sql`脚本创建表结构

### 启动应用

1. 确保MySQL服务已启动，并配置了正确的用户名和密码
2. 运行Spring Boot应用：`mvn spring-boot:run`
3. 访问 http://localhost:8888 打开应用

## API接口

- `POST /api/tasks` - 添加任务
- `GET /api/tasks?sortBy=priority|deadline` - 获取任务列表（支持排序）
- `PUT /api/tasks/{id}/complete` - 标记任务为完成
- `DELETE /api/tasks/{id}` - 删除任务