package com.project.atm;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.atm.controller.AtmCommandLineController;

@SpringBootTest
class AtmApplicationTests {

    @MockBean
    private AtmCommandLineController atmCommandLineController;

	@Test
	void testControllerStartIsCalled_whenAppStarts() throws Exception {
	    Mockito.doNothing().when(atmCommandLineController).start();

	    Mockito.verify(atmCommandLineController, times(1)).start();
	}
}
