/*
* Name:sample example of kotlin mirai
* author:Eritque arcus
* blog:https://blog.csdn.net/qq_40832960
 */
package com.example.plugin

import net.mamoe.mirai.console.plugins.PluginBase
import net.mamoe.mirai.event.events.NewFriendRequestEvent
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.message.FriendMessageEvent
import net.mamoe.mirai.message.GroupMessageEvent
import net.mamoe.mirai.message.MessageEvent
import net.mamoe.mirai.message.TempMessageEvent

object ExamplePluginMain : PluginBase() {

    override fun onEnable() {
        super.onEnable()
        //����Ѽ���
        logger.info("Plugin loaded!")

        subscribeAlways<MessageEvent> {
            //ȫ����Ϣ
        }
        subscribeAlways<GroupMessageEvent> {
            //Ⱥ��Ϣ
        }
        subscribeAlways<FriendMessageEvent> {
            //������Ϣ
            reply("��Ϣ")
        }
        subscribeAlways<TempMessageEvent> {
            //��ʱ��Ϣ
        }
        subscribeAlways<NewFriendRequestEvent> {
            //�Զ�ͬ��Ӻ���

            this.accept()
        }

    }
}