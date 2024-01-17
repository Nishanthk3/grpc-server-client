# grpc-server-client
A GRPC Application
- **_grpc-common:_** Contains .proto files. This module is used by both server and client
  -  Java classes are generated thru below maven plugin
```
    <groupId>org.xolstice.maven.plugins</groupId>
    <artifactId>protobuf-maven-plugin</artifactId>
```
- **_grpc-server:_** Contains grpc server implementation
- **_grpc-client:_** Contains grpc client implementation
  - **_Spring Boot Application:_** 
    - **_Rest Controller:_** exposes REST endpoints to invoke grpc client
