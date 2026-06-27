#!/usr/bin/env sh
#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
#
# Gradle start up script for UN*X
#
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a link to gradle command
PRG="$0"
# Need this for daisy-chained symlinks
while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG="`dirname "$PRG"`/$link"
    fi
done

SAVED="`pwd`"
cd "`dirname "$PRG"`" > /dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" > /dev/null

# Use the maximum available, which will be at least 1.8
JAVA_VERSION="1.8"

# Find Java
if [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java" ]; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

# Check if Java exists
if [ ! -x "$JAVACMD" ]; then
    echo "ERROR: JAVA_HOME is not set and no 'java' executable found in PATH." >&2
    exit 1
fi

CLASSPATH="gradle/wrapper/gradle-wrapper.jar"

# Default JVM options
DEFAULT_JVM_OPTS="-Xmx64m"

exec "$JAVACMD" "$DEFAULT_JVM_OPTS" -cp "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"