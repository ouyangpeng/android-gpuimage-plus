#!/usr/bin/env bash

REPO_DIR="$(realpath "$(dirname "$0")/..")"

if [[ $# -lt 1 ]]; then
    DEFAULT_TARGET_DIR="$REPO_DIR/../android-gpuimage-plus-maven"
    if [[ -d "$DEFAULT_TARGET_DIR" ]]; then
        TARGET_DIR="$DEFAULT_TARGET_DIR"
        echo "Using default target directory $DEFAULT_TARGET_DIR"
    else
        echo "Options: <target_directory>"
        exit 1
    fi

fi

if [[ ! -d "${TARGET_DIR}" ]]; then
    echo "Target directory ${TARGET_DIR} does not exist!"
fi

TARGET_DIR="$(realpath "${TARGET_DIR}")"

BINARY_PATH="org/wysaid/gpuimage-plus"

# find "$TARGET_DIR/$BINARY_PATH" -iname "*.so" | xargs rm -v

cd "$REPO_DIR"

function performBuild() {
    cd "$REPO_DIR"
    if bash "library/src/main/jni/buildJNI" &&
        ./gradlew -p library assembleRelease; then
        echo yeah.
    else
        return -1
    fi
}

if performBuild; then
    # cp -rf -v "$REPO_DIR"/library/src/main/libs/* "$TARGET_DIR/$BINARY_PATH/"
    echo Done!
fi
