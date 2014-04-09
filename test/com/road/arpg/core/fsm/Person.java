/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-27  下午5:06:18
 */
package com.road.arpg.core.fsm;

/**
 * @author Dream.xie
 */
public class Person extends AbstractStateOwner {
    /**
     * 编号
     */
    private Long id;
    /**
     * 名字.
     */
    private String name;

    /**
     * 玩Lol多少局.
     */
    private Integer games = 0;

    /**
     * 玩看电视的集数.
     */
    private Integer episodes = 0;

    /**
     * 休息时间. 毫秒。
     */
    private Integer resetTime = 0;

    /**
     * @param stateMachine
     */
    public Person() {
        StateMachine<Person> stateMachine = new StateMachine<Person>(this,
                        RestState.getInstance(),
                        PersonGlobalState.getInstance());
        setStateMachine(stateMachine);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the games
     */
    public Integer getGames() {
        return games;
    }

    /**
     * @param games
     *            the games to set
     */
    public void setGames(Integer games) {
        this.games = games;
    }

    /**
     * @return the episodes
     */
    public Integer getEpisodes() {
        return episodes;
    }

    /**
     * @param episodes
     *            the episodes to set
     */
    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    /**
     * @return the resetTime
     */
    public Integer getResetTime() {
        return resetTime;
    }

    /**
     * @param resetTime
     *            the resetTime to set
     */
    public void setResetTime(Integer resetTime) {
        this.resetTime = resetTime;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see com.road.arpg.core.fsm.AbstractStateOwner#handlerMessage(com.road.arpg.core.fsm.FsmMessage)
     */
    @Override
    public void handlerMessage(FsmMessage fsmMessage) {
        //值得考虑的是，这里的逻辑是放在每个state里面呢，还是就这样。
        System.out.println("收到消息:" + fsmMessage.getContent());

        PersonMessageType messageType = (PersonMessageType) fsmMessage
                        .getType();
        if (messageType != null) {
            switch (messageType) {
                case GO_TO_WC:
                    //这里可以写具体逻辑
                    System.out.println("状态是去WC");
                    break;
                default:
                    break;
            }
        }
    }
}
