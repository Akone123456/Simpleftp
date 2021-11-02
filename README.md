# 工程简介
Server 端功能需求： 
1. 上传文件接口： 响应标准 http 协议的 post 请求发送来的文件，接收到文件后将文件重命名 （使用 UUID）并按照日期保存至服务器文件系统的不同的目录中（目录格 式 yyyyMMdd），同时将文件大小、文件类型，原始文件名、创建时间、文 件保存目录地址等元数据记录至数据库中，同时将 UUID 返回给客户端。 
2. 下载文件接口： 响应客户端获取文件流的 get 请求，客户端参数为接口 1 中返回的 UUID， 在响应中写入文件流，无其他返回值，异常响应时返回 410 状态码。 
3. 获取文件元数据接口： 响应客户端 get 请求，客户端参数为接口 1 中返回的 UUID，返回值为 Json 格式的元数据信息。 
Server 端技术要求：
 项目基于 Maven 构建，WEB 服务使用嵌入式 Jetty 或 Tomcat 实现，只提供接口 服务，无界面，可以使用任意第三方框架和库。 Client 端功能需求： Client 端 SDK： 封装 HTTP 请求，实现对 Server 端的 3 个接口的调用。 
Client 端技术要求：
项目基于 Maven 构建，尽量不使用第三方框架和依赖，并实现对客户端 SDK 的 单元测试。


# 延伸阅读

