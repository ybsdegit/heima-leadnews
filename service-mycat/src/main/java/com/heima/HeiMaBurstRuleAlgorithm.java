package com.heima;

import io.mycat.config.model.rule.RuleAlgorithm;
import io.mycat.route.function.AbstractPartitionAlgorithm;

import javax.sound.midi.Soundbank;

/**
 * HeiMaBurstRuleAlgorithm
 *
 * @author Paulson
 * @date 2020/1/18 13:35
 */
public class HeiMaBurstRuleAlgorithm extends AbstractPartitionAlgorithm implements RuleAlgorithm {

    // 单组数据容量
    Long volume;
    // 单组DN节点数量
    Integer step;
    // 分片模
    Integer mod;

    public void init(){}


    /**
     *
     * @param columnValue  3-2  1-1
     * @return
     * 分片ID =（dataId/volume）* step +分表ID/mod
     */
    @Override
    public Integer calculate(String columnValue) {
        if (columnValue != null){
            String[] temp = columnValue.split("-");
            if (temp.length == 2){
                try {
                    Long dataId = Long.valueOf(temp[0]);
                    Long burstId = Long.valueOf(temp[1]);
                    int group = (int)(dataId/volume)*step;
                    int pos = group + (int)(burstId%mod);
                    System.out.println("HEIMA RULE INFO ["+columnValue+"]-[{"+pos+"}]");
                    return pos;
                } catch (NumberFormatException e) {
                    System.out.println("HEIMA RULE INFO ["+columnValue+"]-[{"+e.getMessage()+"}]");
                }
            }
        }
        return 0;
    }

    @Override
    public Integer[] calculateRange(String beginValue, String endValue) {
        if (beginValue != null && endValue != null){
            Integer begin = calculate(beginValue);
            Integer end = calculate(endValue);
            if (begin == null || end == null){
                return new Integer[0];
            }
            if (end >= begin){
                int len = end - begin + 1;
                Integer[] re = new Integer[len];
                for (int i = 0; i < len; i++) {
                    re[i] = begin + 1;
                }
                return re;
            }
        }
        return new Integer[0];
    }





    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public void setMod(Integer mod) {
        this.mod = mod;
    }
}
