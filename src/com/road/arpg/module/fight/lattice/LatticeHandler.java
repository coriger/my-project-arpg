/**
 * Copyright(c) 2014 ShenZhen 7road Network Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-3-3  上午10:46:24
 */
package com.road.arpg.module.fight.lattice;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import com.road.arpg.module.fight.map.SceneTest;
import com.road.arpg.module.fight.objects.Living;
import com.road.arpg.module.fight.objects.Physic;
import com.road.arpg.module.fight.objects.abiotic.Abiotic;
import com.road.arpg.module.fight.scene.BaseScene;
import com.road.arpg.module.fight.type.LivingEventType;
import com.road.arpg.module.fight.util.Point;

/**
 * 场景中模块处理器
 * 
 * @author shandong.su
 */
public class LatticeHandler {

    /**
     * 每个块的宽度
     */
    public static final int LATTICE_WIDTH = 200;

    /**
     * 每个块的高度
     */
    public static final int LATTICE_HEITH = 200;

    /**
     * 分块管理器的场景
     */
    private BaseScene scene;

    /**
     * 场景中块的列表
     */
    private SquaredLattice[] latticeMap;

    /**
     * 场景中活跃的块，只有活跃的场景中有玩家数据，有怪物的移动
     */
    private Set<SquaredLattice> activeLattices;

    /**
     * 宽度有多少个分块
     */
    private int widthSize;

    /**
     * 高度有多少个分块
     */
    private int heithSize;

    /**
     * 
     */
    private MapFrame frame;

    /**
     * 
     */
    public LatticeHandler(BaseScene scene) {
        init(scene);
    }

    /**
     * 通过场景，初始化场景处理器
     * 
     * @return
     */
    public boolean init(BaseScene scene) {
        //竖着有多少个分块
        heithSize = scene.getSceneInfo().getHeigh()
                        / LatticeHandler.LATTICE_HEITH;
        if (scene.getSceneInfo().getHeigh() % LatticeHandler.LATTICE_HEITH != 0) {
            heithSize++;
        }
        //横着有多少个分块
        widthSize = scene.getSceneInfo().getWidth()
                        / LatticeHandler.LATTICE_WIDTH;
        if (scene.getSceneInfo().getWidth() % LatticeHandler.LATTICE_WIDTH != 0) {
            widthSize++;
        }
        latticeMap = new SquaredLattice[widthSize * heithSize];
        for (int w = 0; w < widthSize; w++) {
            for (int h = 0; h < heithSize; h++) {
                latticeMap[w * heithSize + h] = new SquaredLattice(w
                                * heithSize + h, this, w, h, widthSize,
                                heithSize);
            }
        }
        activeLattices = new HashSet<SquaredLattice>();
        this.scene = scene;
        this.frame = new MapFrame(this);
        frame.showFrame();
        return true;
    }

    /**
     * 
     * @return
     */
    public MapFrame getFrame() {
        return this.frame;
    }

    /**
     * 每一帧更新
     */
    public void tick(long tickTime) {
        List<SquaredLattice> tempList = new ArrayList<>(activeLattices);
        for (SquaredLattice lattice : tempList) {
            lattice.tick(tickTime);
        }
    }

    /**
     * 获取场景
     * 
     * @return
     */
    public BaseScene getBaseScene() {
        return scene;
    }

    /**
     * 添加生物
     * 
     * @param living
     */
    public void addLiving(Living living) {
        this.frame.addLiving(living);
//       this.frame.paint(this.frame.getGraphics());
        int xLattice = (living.getX()) / LATTICE_WIDTH;
        int yLattice = (living.getY()) / LATTICE_HEITH;
        living.addListener(LivingEventType.MOVING.getValue(),
                        SquaredLattice.LATTICE_LISTENER);
        latticeMap[yLattice * widthSize + xLattice].enterLiving(living);
    }

    /**
     * 移除生物
     * 
     * @param living
     */
    public void leavingLiving(Living living) {
        int xLattice = (living.getX()) / LATTICE_WIDTH;
        int yLattice = (living.getY()) / LATTICE_HEITH;
        living.addListener(LivingEventType.MOVING.getValue(),
                        SquaredLattice.LATTICE_LISTENER);
        latticeMap[yLattice * widthSize + xLattice].leavingLattice(living);
    }

    /**
     * 
     * @param abiotic
     */
    public void addAbiotic(Abiotic abiotic) {
        int xLattice = (abiotic.getX()) / LATTICE_WIDTH;
        int yLattice = (abiotic.getY()) / LATTICE_HEITH;
        abiotic.addListener(LivingEventType.MOVING.getValue(),
                        SquaredLattice.LATTICE_LISTENER);
        latticeMap[yLattice * widthSize + xLattice].enterLiving(abiotic);
    }

    /**
     * 生物从srcPos移动到生物当前点
     * 
     * @param latticeID
     * @param living
     */
    public void handlerMoving(Point srcPos, Living living) {
        this.updatePosition();
        int srcX = (srcPos.getX()) / LatticeHandler.LATTICE_WIDTH;
        int dstX = (living.getX()) / LatticeHandler.LATTICE_WIDTH;
        int srcY = (srcPos.getY()) / LatticeHandler.LATTICE_HEITH;
        int dstY = (living.getY()) / LatticeHandler.LATTICE_HEITH;
        if (srcX == dstX && srcY == dstY) { //如果移动前后在同一块中，那么就不做任何操作了
            return;
        }
        latticeMap[srcY * widthSize + srcX].leavingLattice(living);
        latticeMap[dstY * widthSize + dstX].enterLiving(living);
    }

    /**
     * 
     */
    public void updatePosition() {
        this.frame.paint(this.frame.getGraphics());
    }

    /**
     * 生物激活某块
     * 
     * @param x
     * @param y
     */
    public void active(int latticeID, Living living) {
        latticeMap[latticeID].activeLattice(living);
        activeLattices.add(latticeMap[latticeID]);
    }

    /**
     * 将某个块从激活列表中删除
     * 
     * @param lattice
     */
    public void inactiveLattice(SquaredLattice lattice) {
        this.activeLattices.remove(lattice);
        lattice.inactiveLattice();
    }

    /**
     * 
     * @author shandong.su
     */
    public static class MapFrame extends JFrame {

        /**
         * 
         */
        private static final long serialVersionUID = 111L;

        /**
         * 
         */
        private Map<Integer, Physic> physics;

        /**
         * 
         * @param map
         */
        public MapFrame(LatticeHandler handler) {
            this.setSize(1000, 1000);
            physics = new HashMap<>();
            this.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent arg) {
                    SceneTest.getHero().moveTo(arg.getX(), arg.getY());
                }
            });
        }

        /**
         * 
         * @param physic
         */
        public void addLiving(Physic physic) {
            physics.put(physic.getPhyID(), physic);
        }

        /**
         * 
         */
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, 1000, 1000);
            g.setColor(new Color(50, 50, 50));
            g.drawLine(0, 200, 1000, 200);
            g.drawLine(0, 400, 1000, 400);
            g.drawLine(0, 600, 1000, 600);
            g.drawLine(0, 800, 1000, 800);
            g.drawLine(200, 0, 200, 1000);
            g.drawLine(400, 0, 400, 1000);
            g.drawLine(600, 0, 600, 1000);
            g.drawLine(800, 0, 800, 1000);
            for (Physic physic : physics.values()) {
                g.setColor(physic.getColor());
                g.drawString(physic.getPhyID() + "", physic.getX(),
                                physic.getY());
            }
        }

        /**
         * 
         */
        public void showFrame() {
            this.setVisible(true);
        }
    }
}
