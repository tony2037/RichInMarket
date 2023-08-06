# Commands
build image
```
docker build -t test .
```

run
```
docker run -it test /bin/bash
```

[in the container]
find hadoop classpathes
```
hadoop classpath
```

compile
```
javac -cp /usr/local/hadoop/etc/hadoop:/usr/local/hadoop/share/hadoop/common/lib/*:/usr/local/hadoop/share/hadoop/common/*:/usr/local/hadoop/share/hadoop/hdfs:/usr/local/hadoop/share/hadoop/hdfs/lib/*:/usr/local/hadoop/share/hadoop/hdfs/*:/usr/local/hadoop/share/hadoop/mapreduce/*:/usr/local/hadoop/share/hadoop/yarn:/usr/local/hadoop/share/hadoop/yarn/lib/*:/usr/local/hadoop/share/hadoop/yarn/* filter.java
```

archive
```
jar cvf filter.jar *.class
```

# Reference
* [Docker Hub](https://hub.docker.com/r/ukwa/docker-hadoop/)