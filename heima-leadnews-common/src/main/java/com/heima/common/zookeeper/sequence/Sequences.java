package com.heima.common.zookeeper.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Sequences
 *
 * @author Paulson
 * @date 2020/2/9 21:50
 */
@Component
public class Sequences {
    @Autowired
    private ZookeeperClient client;

    public Long sequenceApLikes(){
        return this.client.sequence(ZkSequenceEnum.AP_LIKES);
    }

    public Long sequenceApReadBehavior(){
        return this.client.sequence(ZkSequenceEnum.AP_READ_BEHAVIOR);
    }

    public Long sequenceApCollection(){
        return this.client.sequence(ZkSequenceEnum.AP_COLLECTION);
    }

    public Long sequenceApUserFollow(){return this.client.sequence(ZkSequenceEnum.AP_USER_FOLLOW);}

    public Long sequenceApUserFan(){return this.client.sequence(ZkSequenceEnum.AP_USER_FAN);}

}
