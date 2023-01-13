# Kotlin protobuf integration examples

This project integrates Kotlin with protobuf and GRPC. This is purely server side and not focused at all on Kotlin Android.

The whole point of this was to analyze Kotlin's support for writing easy GRPC code, especially the DSL. Kotlin GRPC uses
Coroutines for the server code.

The major effort is plumbing rather than code - figuring out how to integrate Kotlin/proto/grpc with Gradle build in Intellij.
I like having a very clean, minimal, streamlined, properly working build script and achieving that took up all the time.

Gradle uses Google's protobuf plugin https://github.com/google/protobuf-gradle-plugin which is the major reason 
for using Gradle — not having to manually install and run the protoc compiler (and its grpc plugin). The Gradle takes care
of it and provides a good IDE experience. 

It uses the following latest versions:
* Kotlin 1.8 and Gradle 7.6 (not validated by either Kotlin or Gradle), but the build/compile only ran properly for me after
the upgrades.
* Gradle protobuf plugin v0.9.1
* GRPC 1.51.0
* Protobuf libs 3.21.12

Based on Google blogs

GRPC https://cloud.google.com/blog/products/application-development/use-grpc-with-kotlin

Protobuf https://developers.googleblog.com/2021/11/announcing-kotlin-support-for-protocol.html

Official Kotlin protobuf example https://developers.google.com/protocol-buffers/docs/kotlintutorial

Official Java protobuf example https://developers.google.com/protocol-buffers/docs/javatutorial

GRPC Kotlin https://github.com/grpc/grpc-kotlin
Examples https://github.com/grpc/grpc-kotlin/tree/master/examples

GCP Cloud run Kotlin grpc example https://github.com/GoogleCloudPlatform/kotlin-samples/blob/main/run/grpc-hello-world-gradle/build.gradle.kts

