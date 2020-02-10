package com.heima.zookeeper.test;

import com.heima.behavior.BehaviorJarApplication;
import com.heima.common.zookeeper.sequence.Sequences;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ZkTest
 *
 * @author Paulson
 * @date 2020/2/9 21:57
 */

@SpringBootTest(classes = BehaviorJarApplication.class)
@RunWith(SpringRunner.class)
public class ZkTest {

    @Autowired
    private Sequences sequences;

    @Test
    public void test1(){
        for (int a = 0; a < 50; a++){
            Long aLong = sequences.sequenceApReadBehavior();
            System.out.println(aLong + "-----------------------");
        }
   }
}
