package ua.vadim.blog.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.vadim.blog.service.TokenManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataControllerTest {

    @Mock
    TokenManager manager;

    @InjectMocks
    DataController controller;

    @Test
    public void getUsername() {
        when(manager.getUsername()).thenReturn(null);
        String username = controller.getUsername();
        verify(manager).getUsername();
    }
}