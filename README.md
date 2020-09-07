# JaxRs demo of the JaxRs interceptor

This is Apache Tomcat-based demo of the Authentication and Authorization framework.
See source of the interceptor at [Github](https://github.com/milanvidakovic/JaxRsInterceptor).

In the WEB_INF/lib you will find the interceptor as agent.jar file.

To use it, you need to place following files in the <TOMCAT_HOME>/lib folder:
* aspectjweaver-1.9.6.jar
* aspectjrt-1.9.6.jar
* aspectjtools-1.9.6.jar

You also need to modify Tomcat startup by adding the following to the VM arguments:

```
-javaagent:<TOMCAT_HOME>/lib/aspectjweaver-1.9.6.jar
```
