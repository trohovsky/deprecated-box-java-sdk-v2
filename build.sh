#!/bin/bash

set -e

# This script provides some extra functionality for building the Box Java SDK
# using Gradle. It takes one optional argument - a path to a build root. A build
# root is a directory where all artifacts and intermediates will be copied to,
# making them easier to archive.
#
# By default, a release build is performed, which includes deploying to Maven.
# Use the DEBUG variable to disable Maven uploading.
#
# Parameters:
#     $1	An optional build root path.
#
# Environment Variables:
#     DEBUG			If set, artifacts will only be installed and not uploaded.
#     GRADLE_PROP	An optional path to a gradle.properties file that will be
#					copied into the BoxJavaLibraryV2 directory.

build_root="$1"

if [ $DEBUG ]; then
	echo "Debug build. Artifacts will not be uploaded."
fi

if [ -n "$GRADLE_PROP" ]; then
	echo "Copying gradle.properties from $GRADLE_PROP."
	cp "$GRADLE_PROP" BoxJavaLibraryV2/gradle.properties
fi

if [ $DEBUG ]; then
	gradle clean build install
else
	gradle clean build uploadArchives
fi

if [ -n "$build_root" ]; then
	mkdir -p "$build_root/Artifacts" &&
		cp -r "BoxJavaLibraryV2/build/libs/"* "$build_root/Artifacts" &&
		cp -r "Examples/HelloWorld/build/libs/"* "$build_root/Artifacts"
	mkdir -p "$build_root/Intermediates" &&
		cp -r "BoxJavaLibraryV2/build/"* "$build_root/Intermediates" &&
		cp -r "Examples/HelloWorld/build/libs/"* "$build_root/Artifacts"
fi
