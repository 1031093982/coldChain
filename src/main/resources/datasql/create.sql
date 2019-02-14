CREATE DATABASE coldchain;
use coldchain;
-- auto-generated definition
CREATE TABLE tbl_account
(
  account VARCHAR(50) NULL
  COMMENT '账号',
  pwd     VARCHAR(50) NULL
  COMMENT '密码',
  phone   VARCHAR(12) NULL
  COMMENT '电话号码'
)
  ENGINE = InnoDB;
-- auto-generated definition
-- auto-generated definition
CREATE TABLE tbl_car
(
  car_id         VARCHAR(60) NOT NULL
  COMMENT 'car_id'
    PRIMARY KEY,
  number_plate   VARCHAR(20) NULL
  COMMENT '车牌号',
  car_gps_x      DOUBLE      NULL
  COMMENT 'gpsx坐标',
  car_gps_y      DOUBLE      NULL
  COMMENT 'gpsy坐标',
  goods_tem      DOUBLE      NULL
  COMMENT '车辆货厢温度',
  delivery_total INT         NULL
  COMMENT '车辆送货次数统计',
  is_deleted     INT         NULL
  COMMENT '是否删除',
  car_type       VARCHAR(10) NULL COMMENT '货车类型',
  state          VARCHAR(10) NULL COMMENT '状态',
  now_place      VARCHAR(40) NULL  COMMENT '当前位置'
)
  ENGINE = InnoDB;
-- auto-generated definition
CREATE TABLE tbl_order
(
  order_id    VARCHAR(60) NOT NULL
  COMMENT 'order_id'
    PRIMARY KEY,
  order_num   VARCHAR(30) NULL
  COMMENT '订单编号',
  departure   VARCHAR(80) NULL
  COMMENT '出发地',
  destination VARCHAR(80) NULL
  COMMENT '目的地',
  tem_status  INT         NULL
  COMMENT '温度是否超限状态',
  tem_max     DOUBLE      NULL
  COMMENT '超限温度数值',
  is_deleted  INT         NULL
  COMMENT '是否删除',
  user_phone  VARCHAR(21) NULL,
  car_num     VARCHAR(20) NULL
)
  ENGINE = InnoDB;

-- auto-generated definition
CREATE TABLE tbl_order_tem
(
  order_id     VARCHAR(30) NULL
  COMMENT '订单编号',
  start_time   MEDIUMTEXT  NULL
  COMMENT '运送开始时间',
  arrival_time MEDIUMTEXT  NULL
  COMMENT '到达目的地时间',
  finish_time  MEDIUMTEXT  NULL
  COMMENT '卸货完成时间'
)
  ENGINE = InnoDB;
-- auto-generated definition
CREATE TABLE tbl_record_history
(
  own_order_id VARCHAR(60) NULL
  COMMENT '所属订单id',
  number_plate VARCHAR(32) NULL
  COMMENT '车牌号',
  record_time  MEDIUMTEXT  NULL
  COMMENT '历史时间',
  record_tem   VARCHAR(20) NULL
  COMMENT '历史温度',
  record_path  VARCHAR(80) NULL
  COMMENT '历史路径',
  is_deleted   INT         NULL
  COMMENT '是否删除'
)
  ENGINE = InnoDB;
-- auto-generated definition
CREATE TABLE tbl_user
(
  uu_id      VARCHAR(60) NOT NULL
  COMMENT 'uu_id'
    PRIMARY KEY,
  name       VARCHAR(20) NULL
  COMMENT '姓名',
  id_card    VARCHAR(20) NULL
  COMMENT '身份证',
  phone      VARCHAR(12) NULL
  COMMENT '电话',
  age        VARCHAR(2)  NULL
  COMMENT '年龄',
  sex        VARCHAR(2)  NULL
  COMMENT '性别',
  user_type  VARCHAR(10) NULL
  COMMENT '用户等级',
  is_deleted INT         NULL
  COMMENT '是否删除'
)
  ENGINE = InnoDB;






