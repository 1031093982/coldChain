# CREATE DATABASE coldchain;
# use coldchain;
CREATE TABLE tbl_user(
  uu_id VARCHAR(60) comment 'uu_id'
    PRIMARY KEY ,
  name VARCHAR(20) comment '姓名',
  id_card VARCHAR(20) comment '身份证',
  phone INT comment '电话',
  age VARCHAR(2) comment '年龄',
  sex VARCHAR(2) comment '性别',
  user_type VARCHAR(10) comment '用户等级',
  is_deleted INT DEFAULT 0
  COMMENT '是否删除'
);
  CREATE  TABLE tbl_car(
   car_id  VARCHAR(60) COMMENT 'car_id'
    PRIMARY KEY ,
    number_plate VARCHAR(20) COMMENT '车牌号',
    car_gps_x DOUBLE COMMENT 'gpsx坐标',
    car_gps_y DOUBLE COMMENT 'gpsy坐标',
    goods_tem DOUBLE COMMENT '车辆货厢温度',
    delivery_total INT COMMENT '车辆送货次数统计',
    is_deleted INT DEFAULT 0
    COMMENT '是否删除'
  );
   CREATE TABLE  tbl_order(
     order_id VARCHAR(60) COMMENT 'order_id'
     PRIMARY KEY ,
     order_num VARCHAR(30) COMMENT '订单编号',
     departure VARCHAR(80) COMMENT '出发地',
     destination VARCHAR(80) COMMENT '目的地',
     tem_status INT DEFAULT 0
     COMMENT'温度是否超限状态',
     tem_max DOUBLE DEFAULT 2
     COMMENT   '超限温度数值',
     is_deleted INT DEFAULT 0
     COMMENT '是否删除'
   );
  CREATE TABLE tbl_order_tem(
    order_id VARCHAR(30) COMMENT '订单编号' references tbl_order(order_num),
    start_time LONG COMMENT '运送开始时间',
    arrival_time LONG COMMENT '到达目的地时间',
    finish_time LONG COMMENT '卸货完成时间'
  );
  CREATE TABLE tbl_record_history(
    own_order_id VARCHAR(60) COMMENT '所属订单id',
    number_plate VARCHAR(32) COMMENT '车牌号',
    record_time  Long COMMENT '历史时间',
    record_tem  DOUBLE COMMENT '历史温度',
    record_path VARCHAR(80) COMMENT '历史路径',
    is_deleted INT DEFAULT 0
    COMMENT '是否删除'
  );