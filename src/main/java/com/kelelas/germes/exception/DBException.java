package com.kelelas.germes.exception;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Slf4j
public class DBException extends RuntimeException{

        public DBException() {
            log.error("DB Exception");
        }

        public DBException(String message) {
            super(message);
            log.error(message);
        }

        public DBException(Throwable cause) {
            super(cause);
            log.error(cause.getMessage());
        }
}
