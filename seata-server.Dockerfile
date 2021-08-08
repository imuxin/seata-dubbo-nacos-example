FROM openjdk:8u302-jre-slim
ARG seataVersion

# Replace apt repository
# add cURL tools
RUN sed -i "s@http://security.debian.org@https://repo.huaweicloud.com@g" /etc/apt/sources.list \
    && sed -i "s@http://deb.debian.org@https://repo.huaweicloud.com@g" /etc/apt/sources.list \
    && apt update && apt install -y curl

ADD https://hub.fastgit.org/seata/seata/releases/download/v${seataVersion}/seata-server-${seataVersion}.tar.gz /
RUN tar -xzf seata-server-${seataVersion}.tar.gz  \
    && rm seata-server-${seataVersion}.tar.gz

WORKDIR /seata/seata-server-${seataVersion}
