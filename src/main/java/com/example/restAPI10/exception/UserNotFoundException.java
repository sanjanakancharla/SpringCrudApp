package com.example.restAPI10.exception;

public class UserNotFoundException extends RuntimeException {

        private static final long serialVersionUID = -8485815202244150498L;

        public UserNotFoundException() {
            super("User id not found");
        }

    }

