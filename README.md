centos7.6安装ftp
参考 https://blog.csdn.net/shj_php/article/details/86572178


分离打包
在pom.xml文件plugins添加
```xml
<plugin>
    <configuration>
        <layout>ZIP</layout>
        <includes>
            <include>
                <groupId>nothing</groupId>
                <artifactId>nothing</artifactId>
            </include>
        </includes>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>repackage</goal>
            </goals>
        </execution>
    </executions>
</plugin>
````
运行程序命令
````shell script
java -Dloader.path="lib/" -jar ROOT.jar
````