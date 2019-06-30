package org.ko.sigma.rest.async.component;

import org.apache.commons.lang.StringUtils;
import org.ko.sigma.core.support.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueListener.class);

    @Autowired private MockQueue mockQueue;

    @Autowired private DeferredResultHolder deferredResultHolder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        new Thread(() -> {
            while (true) {
                if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
                    //完成订单的订单号
                    String orderNumber = mockQueue.getCompleteOrder();
                    LOGGER.info("返回订单处理结果: {}", orderNumber);
                    deferredResultHolder.getMap().get(orderNumber).setResult(
                            new Response<>("place order success!"));

                    mockQueue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        LOGGER.error("QueueListener#onApplicationEvent exception: {}", e);
                    }
                }
            }
        }).start();

    }
}
