package com.journalapp.journalApp.service;

import com.journalapp.journalApp.entity.User;
import com.journalapp.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @Disabled
//    @Test
    @ParameterizedTest
//    @CsvSource({
//            "ram",
//            "sham",
//            "shubham"
//    })

    @ArgumentsSource(UserArgumentsProvider.class)
    public void testFindByUserName(User user)
    {
//       assertEquals(4,3+5);
        assertTrue(userService.saveU(user));
    }


    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })

    public void test(int a , int b, int expected)
    {
        assertEquals(expected,a+b);
    }



}
