package spring.service;

import spring.config.MethodLock;
import spring.config.ParameterLock;

/**
 * Created by frinder6 on 2017/3/3.
 */
public interface InventoryService {

    @MethodLock(tableName = "inventory_table")
    void reduce(@ParameterLock String inventoryId);

    void print();

}
