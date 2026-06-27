@REM
@REM Copyright 2015 the original author or authors.
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      https://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM

@if "%DEBUG%" == "true" @echo on
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for variables
setlocal

set CLASSPATH=gradle\wrapper\gradle-wrapper.jar
set DEFAULT_JVM_OPTS=-Xmx64m

@if DEFINED JAVA_HOME goto findJavaFromJavaHome

if defined java (
    set JAVACMD=java
    goto checkJAVACMD
)

echo ERROR: JAVA_HOME is not set and no 'java' executable found in PATH. >&2
exit /b 1

:findJavaFromJavaHome
if exist "%JAVA_HOME%\bin\java.exe" (
    set JAVACMD="%JAVA_HOME%\bin\java.exe"
    goto checkJAVACMD
)

if exist "%JAVA_HOME%\java.exe" (
    set JAVACMD="%JAVA_HOME%\java.exe"
    goto checkJAVACMD
)

:checkJAVACMD
if not exist "%JAVACMD%" (
    echo ERROR: JAVA_HOME is not set and no 'java' executable found in PATH. >&2
    exit /b 1
)

"%JAVACMD%" %DEFAULT_JVM_OPTS% -cp "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*