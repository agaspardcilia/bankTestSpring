package com.example.banktest.services;

import com.example.banktest.BanktestApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BanktestApplication.class)
public class IDServiceTests {
    private IDService idService;

    @Before
    public void init() {
        this.idService = new IDService();
    }

    @Test
    public void AssertThatItStartsAtOne() {
        assertThat(this.idService.nextID()).isEqualTo(1);
    }

    @Test
    public void AssertThatAnIdIsUnique() {
        var previousIDs = new HashSet<Integer>();
        for(int i = 0; i < 1000; i++) {
            var currentId = this.idService.nextID();
            assertThat(previousIDs).doesNotContain(currentId);
            previousIDs.add(currentId);
        }
    }
}
