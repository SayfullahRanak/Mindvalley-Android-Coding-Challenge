FROM gitpod/workspace-full

# Install Java and Android SDK
RUN sudo apt-get update && sudo apt-get install -y openjdk-11-jdk
RUN wget -q https://dl.google.com/android/repository/commandlinetools-linux-6858069_latest.zip -O android-sdk.zip \
    && unzip android-sdk.zip -d $ANDROID_HOME/cmdline-tools \
    && rm android-sdk.zip
RUN yes | $ANDROID_HOME/cmdline-tools/tools/bin/sdkmanager --licenses
RUN $ANDROID_HOME/cmdline-tools/tools/bin/sdkmanager "platform-tools" "platforms;android-29" "build-tools;29.0.3"

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
