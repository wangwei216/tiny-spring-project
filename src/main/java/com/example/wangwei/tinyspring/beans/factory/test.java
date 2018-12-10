package com.example.wangwei.tinyspring.beans.factory;

import java.util.HashMap;

public class test {

    public static void main(String[] args){


        String s = "8c92109888e8cdf9d66dc7e463025574";
        System.out.println(s.length());

        HashMap m = new HashMap();

        AbstractBeanFactory abstractBeanFactory = new AbstractBeanFactory();
        System.out.println(abstractBeanFactory.getClass().getName());
        System.out.println(abstractBeanFactory.getClass());

//        //到这步是把新扩容的桶数组给成员变量的那个桶数组
//        final Node<K, V>[] resize() {
//            Node<K, V>[] oldTab = table;
//            int oldCap = (oldTab == null) ? 0 : oldTab.length;// 数组长度
//            int oldThr = threshold;// 临界值
//            int newCap, newThr = 0;
//            if (oldCap > 0) {
//                // 扩容
//                if (oldCap >= MAXIMUM_CAPACITY) {
//                    // 原数组长度大于最大容量(1073741824) 则将threshold设为Integer.MAX_VALUE=2147483647
//                    // 接近MAXIMUM_CAPACITY的两倍
//                    threshold = Integer.MAX_VALUE;
//                    return oldTab;
//                } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
//                    // 新数组长度 是原来的2倍，
//                    // 临界值也扩大为原来2倍
//                    newThr = oldThr << 1;
//                }
//            } else if (oldThr > 0) {
//                // 如果原来的thredshold大于0则将容量设为原来的thredshold
//                // 在第一次带参数初始化时候会有这种情况
//                newCap = oldThr;
//            } else {
//                // 在默认无参数初始化会有这种情况
//                newCap = DEFAULT_INITIAL_CAPACITY;// 16
//                newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);// 0.75*16=12
//            }
//            if (newThr == 0) {
//                // 如果新 的容量 ==0
//                float ft = (float) newCap * loadFactor;// loadFactor 哈希加载因子 默认0.75,可在初始化时传入,16*0.75=12 可以放12个键值对
//                newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ? (int) ft : Integer.MAX_VALUE);
//            }
//            threshold = newThr;// 将临界值设置为新临界值
//            @SuppressWarnings({ "rawtypes", "unchecked" })
//            // 扩容
//                    Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
//            table = newTab;
//            // 如果原来的table有数据，则将数据复制到新的table中
//            if (oldTab != null) {
//                // 根据容量进行循环整个数组，将非空元素进行复制
//                for (int j = 0; j < oldCap; ++j) {
//                    Node<K, V> e;
//                    // 获取数组的第j个元素
//                    if ((e = oldTab[j]) != null) {
//                        oldTab[j] = null;
//                        // 如果链表只有一个，则进行直接赋值
//                        if (e.next == null)
//                            // e.hash & (newCap - 1) 确定元素存放位置
//                            newTab[e.hash & (newCap - 1)] = e;
//                            //  此处省略红黑树
//                        else {
//                            // 进行链表复制
//                            // 方法比较特殊： 它并没有重新计算元素在数组中的位置
//                            // 而是采用了 原始位置加原数组长度的方法计算得到位置
//                            Node<K, V> loHead = null, loTail = null;
//                            Node<K, V> hiHead = null, hiTail = null;
//                            Node<K, V> next;
//                            do {
//                                /*********************************************/
//                                /**
//                                 * 注: e本身就是一个链表的节点，它有 自身的值和next(链表的值)，但是因为next值对节点扩容没有帮助，
//                                 * 所有在下面讨论中，我近似认为 e是一个只有自身值，而没有next值的元素。
//                                 */
//                                /*********************************************/
//                                next = e.next;
//                                // 注意：不是(e.hash & (oldCap-1));而是(e.hash & oldCap)
//
//                                // (e.hash & oldCap) 得到的是 元素的在数组中的位置是否需要移动,示例如下
//                                // 示例1：
//                                // e.hash=10 0000 1010
//                                // oldCap=16 0001 0000
//                                //	 &   =0	 0000 0000       比较高位的第一位 0
//                                //结论：元素位置在扩容后数组中的位置没有发生改变
//
//                                // 示例2：
//                                // e.hash=17 0001 0001
//                                // oldCap=16 0001 0000
//                                //	 &   =1	 0001 0000      比较高位的第一位   1
//                                //结论：元素位置在扩容后数组中的位置发生了改变，新的下标位置是原下标位置+原数组长度
//
//                                // (e.hash & (oldCap-1)) 得到的是下标位置,示例如下
//                                //   e.hash=10 0000 1010
//                                // oldCap-1=15 0000 1111
//                                //      &  =10 0000 1010
//
//                                //   e.hash=17 0001 0001
//                                // oldCap-1=15 0000 1111
//                                //      &  =1  0000 0001
//
//                                //新下标位置
//                                //   e.hash=17 0001 0001
//                                // newCap-1=31 0001 1111    newCap=32
//                                //      &  =17 0001 0001    1+oldCap = 1+16
//
//                                //元素在重新计算hash之后，因为n变为2倍，那么n-1的mask范围在高位多1bit(红色)，因此新的index就会发生这样的变化：
//                                //参考博文：[Java8的HashMap详解](https://blog.csdn.net/login_sonata/article/details/76598675)
//                                // 0000 0001->0001 0001
//
//                                //这里是判断扩容之后的
//                                if ((e.hash & oldCap) == 0) {
//                                    // 如果原元素位置没有发生变化
//                                    if (loTail == null)
//                                        loHead = e;// 确定首元素
//                                        // 第一次进入时	  e   -> aa  ; loHead-> aa
//                                    else
//                                        loTail.next = e;
//                                    //第二次进入时		loTail-> aa  ;    e  -> bb ;  loTail.next -> bb;而loHead和loTail是指向同一块内存的，所以loHead.next 地址为 bb
//                                    //第三次进入时		loTail-> bb  ;    e  -> cc ;  loTail.next 地址为 cc;loHead.next.next = cc
//                                    loTail = e;
//                                    // 第一次进入时   	  e   -> aa  ; loTail-> aa loTail指向了和  loHead相同的内存空间
//                                    // 第二次进入时               e   -> bb  ; loTail-> bb loTail指向了和  loTail.next（loHead.next）相同的内存空间   loTail=loTail.next
//                                    // 第三次进入时               e   -> cc  ; loTail-> cc loTail指向了和  loTail.next(loHead.next.next)相同的内存
//                                } else {
//                                    //与上面同理
//
//                                    if (hiTail == null)
//                                        hiHead = e;
//                                    else
//                                        hiTail.next = e;
//                                    hiTail = e;
//                                }
//                            } while ((e = next) != null);//这一块就是 旧链表迁移新链表
//                            //总结：1.8中 旧链表迁移新链表    链表元素相对位置没有变化; 实际是对对象的内存地址进行操作
//                            //在1.7中  旧链表迁移新链表        如果在新表的数组索引位置相同，则链表元素会倒置
//                            if (loTail != null) {
//                                loTail.next = null;// 将链表的尾节点 的next 设置为空
//                                newTab[j] = loHead;
//                            }
//                            if (hiTail != null) {
//                                hiTail.next = null;// 将链表的尾节点 的next 设置为空
//                                newTab[j + oldCap] = hiHead;
//                            }
//                        }
//                    }
//                }
//            }
//            return newTab;
//        }




    }
}
