#!/bin/sh
cd `dirname "$0"`
APP_HOME=`pwd`

INIT() {
  #java setting
}

START() {
  INIT;
  #program start
}

STOP() {
  #program stop
}

PROCESS() {
  USE_MODE="${1}";
  case "${USE_MODE}" in
    "start")
      START;
      ;;
    "stop")
      STOP;
      ;;
    *)
      echo "Invalid mode";
      return 1;
      ;;
  esac;
  return 0;
}

PROCESS "${1}";