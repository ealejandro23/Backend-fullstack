#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/MetodoEnvioController.java:[152,9] binary numbers must contain at least one binary digit
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/MetodoEnvioController.java:[152,12] ';' expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/MetodoEnvioController.java:[153,4] illegal start of expression
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/MetodoEnvioController.java:[153,7] illegal start of expression
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/MetodoEnvioController.java:[153,10] ';' expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/MetodoEnvioController.java:[153,49] ';' expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[17,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[19,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[20,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[21,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[22,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[23,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[24,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[25,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[26,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[27,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[28,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[29,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[30,1] class, interface, enum, or record expected
#14 8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[31,1] class, interface, enum, or record expected
#14 8.989 [ERROR] -> [Help 1]
#14 8.989 [ERROR] 
#14 8.989 [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
#14 8.989 [ERROR] Re-run Maven using the -X switch to enable full debug logging.
#14 8.989 [ERROR] 
#14 8.989 [ERROR] For more information about the errors and possible solutions, please read the following articles:
#14 8.989 [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
#14 ERROR: process "/bin/sh -c mvn clean package -DskipTests" did not complete successfully: exit code: 1
------
 > [builder 5/5] RUN mvn clean package -DskipTests:
8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[29,1] class, interface, enum, or record expected
8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[30,1] class, interface, enum, or record expected
8.989 [ERROR] /app/src/main/java/Proyecto_EFA/demo/controller/VentaController.java:[31,1] class, interface, enum, or record expected
8.989 [ERROR] -> [Help 1]
8.989 [ERROR] 
8.989 [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
8.989 [ERROR] Re-run Maven using the -X switch to enable full debug logging.
8.989 [ERROR] 
8.989 [ERROR] For more information about the errors and possible solutions, please read the following articles:
8.989 [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
------
Dockerfile:6
--------------------
   4 |     COPY pom.xml .
   5 |     COPY src ./src
   6 | >>> RUN mvn clean package -DskipTests
   7 |     
   8 |     # Etapa 2: Crear imagen final con Temurin 21 JRE
--------------------
error: failed to solve: process "/bin/sh -c mvn clean package -DskipTests" did not complete successfully: exit code: 1
