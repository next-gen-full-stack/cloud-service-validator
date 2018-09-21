set -e

COMMAND=${1:-build}

function buildApp() {
    echo "### BUILD ###"
    ./gradlew goJF
    ./gradlew build
}

function testApp() {
    echo "### TEST ###"
    ./gradlew goJF
    ./gradlew test
}

function runApp() {
    echo "### RUN ###"
    ./gradlew goJF
    ./gradlew bootRun
}

function cleanApp() {
    echo "### CLEAN ###"
    ./gradlew goJF
    ./gradlew clean
}

# Prints a simple usage description.
function usage() {
    echo "Usage: ${0} <Command>"
    echo "Command can be [build/test/run] [profile]"
    exit 1
}

# Switch command and execute corresponding function.
case ${COMMAND} in
    build)
        buildApp ${PROFILE_ARG};;
    test)
        testApp ${PROFILE_ARG};;
    run)
        runApp ${PROFILE_ARG};;
    clean)
        cleanApp ${PROFILE_ARG};;
    *)
        usage;;
esac
