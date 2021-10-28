package com.example.restAPI10.exception;

import com.example.restAPI10.entity.Users;

public class UserInputIsEmpty extends RuntimeException {


        private static final long serialVersionUID = -8485815202244150498L;

        public UserInputIsEmpty() {
            super("User input is empty");
        }
    }



