FROM gitpod/workspace-full-vnc

USER root

# RUN add-apt-repository universe
# RUN apt update
# RUN apt -y install graphviz
RUN sudo apt-get update && sudo apt-get install -y matchbox && sudo apt-get clean && sudo rm -rf /var/cache/apt/* && sudo rm -rf /var/lib/apt/lists/* && sudo rm -rf /tmp/*


USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
        sdk install java 17.0.3-ms && \
        sdk default java 17.0.3-ms"
