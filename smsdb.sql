/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : smsdb

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 24/01/2021 09:47:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item`  (
  `cart_item_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '产品类别id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品类别名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '价格',
  `number` int(0) NULL DEFAULT 0 COMMENT '数量',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  PRIMARY KEY (`cart_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart_item
-- ----------------------------
INSERT INTO `cart_item` VALUES (1, 0, 'string', 0, 'string', 'string', 'string', 'string', 0.00, 20, 0);

-- ----------------------------
-- Table structure for clientele_address
-- ----------------------------
DROP TABLE IF EXISTS `clientele_address`;
CREATE TABLE `clientele_address`  (
  `addr_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clientele_id` int(0) NOT NULL COMMENT '客户id',
  `def_address` int(0) NULL DEFAULT 0 COMMENT '是否默认地址（1表示默认）',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区、县',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收件手机号',
  `recipient` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收件人',
  `zip_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮政编码',
  PRIMARY KEY (`addr_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_address
-- ----------------------------
DROP TABLE IF EXISTS `order_address`;
CREATE TABLE `order_address`  (
  `pk_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clientele_id` int(0) NOT NULL COMMENT '客户id',
  `order_id` int(0) NULL DEFAULT NULL COMMENT '订单id',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单号',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区、县',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收件手机号',
  `recipient` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收件人',
  `zip_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮政编码',
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户订单收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_accessory
-- ----------------------------
DROP TABLE IF EXISTS `sys_accessory`;
CREATE TABLE `sys_accessory`  (
  `pk_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sub_id` bigint(0) NOT NULL COMMENT '目的产品编号',
  `materiel_id` bigint(0) NULL DEFAULT NULL COMMENT '来源产品编号',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储文件名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '展示文件名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片url',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传人',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_adjustment
-- ----------------------------
DROP TABLE IF EXISTS `sys_adjustment`;
CREATE TABLE `sys_adjustment`  (
  `adjustment_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(0) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `adjustment_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '调整类型(0新增调整，1盘点调整)',
  `adjustment_time` datetime(0) NULL DEFAULT NULL COMMENT '调整时间',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调整负责人',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '调整人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`adjustment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '调整单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_adjustment_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_adjustment_sub`;
CREATE TABLE `sys_adjustment_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `adjustment_id` int(0) NOT NULL COMMENT '主表id',
  `warehouse_id` int(0) NULL DEFAULT NULL COMMENT '仓库id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `number` int(0) NULL DEFAULT NULL COMMENT '现存量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `adjust_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '调整金额',
  `adjust_num` int(0) NULL DEFAULT 0 COMMENT '调整数量',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调整原因',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `adjust_total_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '调整前的总金额',
  `adjust_number` int(0) NULL DEFAULT 0 COMMENT '调整前的总数量',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '调整单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `category_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '类别编码',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '类别名称',
  `parent_id` int(0) NULL DEFAULT 0 COMMENT '父级类别id',
  `category` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型（0产品类别 1客户类别）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(0) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品类别封面图',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '自定义类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES (1, 'cplb', '产品分类', 0, '0', '0', '0', '超级管理员', '2020-11-16 11:57:01', '超级管理员', '2020-11-28 09:58:36', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (2, 'ricyp', '女装', 1, '0', '0', '0', '超级管理员', '2020-11-16 11:57:42', '超级管理员', '2020-11-28 09:59:10', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (3, 'khlb', '客户分类', 0, '1', '0', '0', '超级管理员', '2020-11-16 12:27:17', '超级管理员', '2020-11-28 09:58:50', NULL, 1, NULL);
INSERT INTO `sys_category` VALUES (4, 'gzkh', '广州客户', 3, '1', '0', '0', '超级管理员', '2020-11-16 12:27:40', '超级管理员', '2020-11-16 12:27:40', NULL, 1, NULL);
INSERT INTO `sys_category` VALUES (5, 'nanzhuang', '男装', 1, '0', '0', '0', '超级管理员', '2020-11-28 09:59:22', '超级管理员', '2020-11-28 09:59:22', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (6, 'xiezi', '鞋子', 1, '0', '0', '0', '超级管理员', '2020-11-28 09:59:45', '超级管理员', '2020-11-28 09:59:45', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (7, 'sbiao', '手表', 1, '0', '0', '0', '超级管理员', '2020-11-28 10:00:01', '超级管理员', '2020-11-28 10:00:01', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (8, 'muywju', '母婴玩具', 1, '0', '0', '0', '超级管理员', '2020-11-28 10:00:16', '超级管理员', '2020-11-28 10:00:16', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (9, 'neiyi', '内衣', 2, '0', '0', '0', '超级管理员', '2020-11-28 10:00:31', '超级管理员', '2020-11-28 10:00:31', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (10, 'nvxue', '靴子', 2, '0', '0', '0', '超级管理员', '2020-11-28 10:00:49', '超级管理员', '2020-11-28 10:00:49', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (11, 'xz', '胸罩', 2, '0', '0', '0', '超级管理员', '2020-11-28 10:01:15', '超级管理员', '2020-11-28 10:01:15', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (12, 'waz', '袜子', 5, '0', '0', '0', '超级管理员', '2020-11-28 10:01:29', '超级管理员', '2020-11-28 10:01:29', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (13, 'sjiaoku', '三角裤', 5, '0', '0', '0', '超级管理员', '2020-11-28 10:01:44', '超级管理员', '2020-11-28 10:01:44', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (14, 'sijiaoku', '四角裤', 5, '0', '0', '0', '超级管理员', '2020-11-28 10:01:59', '超级管理员', '2020-11-28 10:01:59', NULL, 1, 'https://picsum.photos/200');
INSERT INTO `sys_category` VALUES (15, 'waitao', '外套', 5, '0', '0', '0', '超级管理员', '2020-11-28 10:02:10', '超级管理员', '2020-11-28 10:02:10', NULL, 1, 'https://picsum.photos/200');

-- ----------------------------
-- Table structure for sys_clientele
-- ----------------------------
DROP TABLE IF EXISTS `sys_clientele`;
CREATE TABLE `sys_clientele`  (
  `clientele_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '客户名称',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '客户类别id',
  `legal_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '法人',
  `corporation_time` date NULL DEFAULT NULL COMMENT '合作时间',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `abbreviation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司简称',
  `certificate_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '统一社会信用证号',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '等级',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司地址',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '业务员id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '启用状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`clientele_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_clientele
-- ----------------------------
INSERT INTO `sys_clientele` VALUES (1, '1', '客户001', 4, NULL, '2020-10-27', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '0', '超级管理员', '2020-11-16 12:28:06', '超级管理员', '2020-11-16 12:28:06', NULL, 1);
INSERT INTO `sys_clientele` VALUES (2, '2', '客户002', 4, NULL, '2020-10-28', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '0', '超级管理员', '2020-11-16 12:28:24', '超级管理员', '2020-11-16 12:28:24', NULL, 1);

-- ----------------------------
-- Table structure for sys_clientele_product
-- ----------------------------
DROP TABLE IF EXISTS `sys_clientele_product`;
CREATE TABLE `sys_clientele_product`  (
  `product_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clientele_id` int(0) NOT NULL COMMENT '客户id',
  `materiel_id` int(0) NOT NULL COMMENT '产品编号',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(12, 4) NULL DEFAULT NULL COMMENT '单价',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '调价时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调价人',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '产品类别id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品类别',
  `pk_id` bigint(0) NULL DEFAULT NULL COMMENT '产品型号id',
  `create_id` int(0) NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_clientele_product
-- ----------------------------
INSERT INTO `sys_clientele_product` VALUES (1, 1, 4, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, '个', 44.6600, '2020-11-16 14:08:45', '超级管理员', '2020-11-16 14:08:45', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (2, 1, 4, 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, '个', 44.6600, '2020-11-16 14:08:45', '超级管理员', '2020-11-16 14:08:45', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (3, 1, 4, 4, 'xilianp', '洗脸盆', '内用、不易破', '中', NULL, NULL, '个', 44.6600, '2020-11-16 14:08:45', '超级管理员', '2020-11-16 14:08:45', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (4, 1, 3, 3, 'maojin', '毛巾', '洗脸、洗澡', '毛绒', NULL, NULL, '条', 22.8900, '2020-11-16 14:08:46', '超级管理员', '2020-11-16 14:08:46', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (5, 1, 3, 3, 'maojin', '毛巾', '洗脸、洗澡', '丝绸', NULL, NULL, '条', 22.8900, '2020-11-16 14:08:46', '超级管理员', '2020-11-16 14:08:46', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (6, 1, 3, 3, 'maojin', '毛巾', '洗脸、洗澡', '尼龙布', NULL, NULL, '条', 22.8900, '2020-11-16 14:08:46', '超级管理员', '2020-11-16 14:08:46', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (7, 1, 2, 2, 'beiz', '杯子', '耐高温', '大号', NULL, NULL, '杯', 33.6000, '2020-11-16 14:08:46', '超级管理员', '2020-11-16 14:08:46', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (8, 1, 2, 2, 'beiz', '杯子', '耐高温', '中号', NULL, NULL, '杯', 33.6000, '2020-11-16 14:08:46', '超级管理员', '2020-11-16 14:08:46', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (9, 1, 2, 2, 'beiz', '杯子', '耐高温', '小号', NULL, NULL, '杯', 33.6000, '2020-11-16 14:08:46', '超级管理员', '2020-11-16 14:08:46', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (10, 1, 1, 1, 'yg', '牙膏', '好用、便宜', '大人', NULL, NULL, '支', 25.9000, '2020-11-16 14:08:46', '超级管理员', '2020-11-16 14:08:46', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (11, 1, 1, 1, 'yg', '牙膏', '好用、便宜', '小孩', NULL, NULL, '支', 25.9000, '2020-11-16 14:08:52', '超级管理员', '2020-11-16 14:08:52', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (12, 1, 1, 1, 'yg', '牙膏', '好用、便宜', '老人', NULL, NULL, '支', 25.9000, '2020-11-16 14:08:53', '超级管理员', '2020-11-16 14:08:53', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (13, 2, 4, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, '个', 44.6600, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (14, 2, 4, 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, '个', 44.6600, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (15, 2, 4, 4, 'xilianp', '洗脸盆', '内用、不易破', '中', NULL, NULL, '个', 44.6600, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (16, 2, 3, 3, 'maojin', '毛巾', '洗脸、洗澡', '毛绒', NULL, NULL, '条', 22.8900, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (17, 2, 3, 3, 'maojin', '毛巾', '洗脸、洗澡', '丝绸', NULL, NULL, '条', 22.8900, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (18, 2, 3, 3, 'maojin', '毛巾', '洗脸、洗澡', '尼龙布', NULL, NULL, '条', 22.8900, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (19, 2, 2, 2, 'beiz', '杯子', '耐高温', '大号', NULL, NULL, '杯', 33.6000, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (20, 2, 2, 2, 'beiz', '杯子', '耐高温', '中号', NULL, NULL, '杯', 33.6000, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (21, 2, 2, 2, 'beiz', '杯子', '耐高温', '小号', NULL, NULL, '杯', 33.6000, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');
INSERT INTO `sys_clientele_product` VALUES (22, 2, 1, 1, 'yg', '牙膏', '好用、便宜', '大人', NULL, NULL, '支', 25.9000, '2020-11-16 14:09:30', '超级管理员', '2020-11-16 14:09:30', '超级管理员', 2, '日常用品', NULL, 1, '0');

-- ----------------------------
-- Table structure for sys_code_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_rule`;
CREATE TABLE `sys_code_rule`  (
  `pk_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '流水号',
  `ts` datetime(0) NOT NULL COMMENT '时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`pk_id`, `prefix`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '编码规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门编码',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '祖级列表',
  `parent_id` int(0) NULL DEFAULT 0 COMMENT '父部门id',
  `dept_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '部门类型（0部门 1公司）',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在1代表删除）',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门档案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 'zb', '总部', '0', 0, '0', NULL, NULL, NULL, NULL, '0', '0', '超级管理员', '2020-11-16 12:24:06', '超级管理员', '2020-11-16 12:24:06', 1);
INSERT INTO `sys_dept` VALUES (2, 'dev', '开发部', '0,1', 1, '0', NULL, NULL, NULL, NULL, '0', '0', '超级管理员', '2020-11-16 12:24:48', '超级管理员', '2020-11-16 12:24:48', 1);
INSERT INTO `sys_dept` VALUES (3, 'qwe', 'qwe', '0,1', 1, '0', NULL, NULL, NULL, NULL, '0', '0', '超级管理员', '2020-11-23 17:40:13', '超级管理员', '2020-11-23 17:40:13', 1);
INSERT INTO `sys_dept` VALUES (4, 'vvvv', 'vvv', '0,1,2', 2, '0', NULL, NULL, NULL, NULL, '0', '0', '超级管理员', '2020-11-23 17:40:29', '超级管理员', '2020-11-23 17:40:29', 1);
INSERT INTO `sys_dept` VALUES (5, '111', '111', '0,1,2', 2, '0', NULL, NULL, NULL, NULL, '0', '0', '超级管理员', '2020-11-23 17:42:17', '超级管理员', '2020-11-23 17:42:17', 1);
INSERT INTO `sys_dept` VALUES (6, 'vv11', 'vv11', '0,1,2,5', 5, '0', NULL, NULL, NULL, NULL, '0', '0', '超级管理员', '2020-11-23 17:50:05', '超级管理员', '2020-11-23 17:50:05', 1);

-- ----------------------------
-- Table structure for sys_finance_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_finance_init`;
CREATE TABLE `sys_finance_init`  (
  `init_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `receive_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '应收金额',
  `finance_time` datetime(0) NULL DEFAULT NULL COMMENT '财务日期',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `has_verifica_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '已核销金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`init_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '财务初始化表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_inventory
-- ----------------------------
DROP TABLE IF EXISTS `sys_inventory`;
CREATE TABLE `sys_inventory`  (
  `inventory_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(0) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `inventory_time` datetime(0) NULL DEFAULT NULL COMMENT '盘点时间',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '盘点负责人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盘点负责人',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '审核者',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `adjustment_id` int(0) NULL DEFAULT NULL COMMENT '调整单id',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`inventory_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '盘点单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_inventory_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_inventory_sub`;
CREATE TABLE `sys_inventory_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `inventory_id` int(0) NOT NULL COMMENT '初始化主表id',
  `warehouse_id` int(0) NULL DEFAULT NULL COMMENT '仓库id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '现存总金额',
  `number` int(0) NULL DEFAULT NULL COMMENT '现存数量',
  `reality_num` int(0) NULL DEFAULT 0 COMMENT '实盘数',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `adjustment_id` int(0) NULL DEFAULT NULL COMMENT '调整单id',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '盘点单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_materiel
-- ----------------------------
DROP TABLE IF EXISTS `sys_materiel`;
CREATE TABLE `sys_materiel`  (
  `materiel_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '产品编号',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '产品类型id',
  `category_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品类型编码',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品类型名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '基本单位编码',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '基本单位名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `max_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最高价',
  `min_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最低价',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`materiel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品档案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_materiel
-- ----------------------------
INSERT INTO `sys_materiel` VALUES (1, 'yg', '牙膏', 9, NULL, NULL, '好用、便宜', NULL, NULL, 1, 'zhi', '支', 25.90, NULL, NULL, '0', '0', '2020-11-16 12:00:29', '超级管理员', '2020-11-16 12:00:29', '超级管理员', NULL, 1);
INSERT INTO `sys_materiel` VALUES (2, 'beiz', '杯子', 9, NULL, NULL, '耐高温', NULL, NULL, 2, 'bei', '杯', 33.60, NULL, NULL, '0', '0', '2020-11-16 12:01:17', '超级管理员', '2020-11-16 12:01:17', '超级管理员', NULL, 1);
INSERT INTO `sys_materiel` VALUES (3, 'maojin', '毛巾', 9, NULL, NULL, '洗脸、洗澡', NULL, NULL, 3, 'tiao', '条', 22.89, NULL, NULL, '0', '0', '2020-11-16 12:02:37', '超级管理员', '2020-11-16 12:02:37', '超级管理员', NULL, 1);
INSERT INTO `sys_materiel` VALUES (4, 'xilianp', '洗脸盆', 9, NULL, NULL, '内用、不易破', NULL, NULL, 4, 'ge', '个', 44.66, NULL, NULL, '0', '0', '2020-11-16 12:10:22', '超级管理员', '2020-11-16 12:10:22', '超级管理员', NULL, 1);
INSERT INTO `sys_materiel` VALUES (5, 'bzdsdd', '不知道啥东东', 9, NULL, NULL, '不知道啥东东', NULL, NULL, 4, 'ge', '个', 99.99, NULL, NULL, '0', '0', '2020-11-20 16:32:28', '超级管理员', '2020-11-20 16:32:28', '超级管理员', NULL, 1);
INSERT INTO `sys_materiel` VALUES (6, 'yf', '衣服', 9, 'ricyp', '日常用品', '123434512512', NULL, NULL, 3, 'tiao', '条', 123.00, 110.00, 130.00, '0', '0', '2020-11-27 12:18:29', '超级管理员', '2020-11-27 15:42:46', '超级管理员', NULL, 1);
INSERT INTO `sys_materiel` VALUES (7, 'asd', '安达市', 9, NULL, NULL, '阿萨德', NULL, NULL, 3, 'tiao', '条', 12.00, NULL, NULL, '0', '0', '2020-12-03 18:09:19', '超级管理员', NULL, NULL, NULL, 1);
INSERT INTO `sys_materiel` VALUES (8, '123', '321', 10, NULL, NULL, '321', '321', '321', 2, 'bei', '杯', 231321.00, 321.00, 213.00, '0', '0', '2021-01-22 17:46:47', '超级管理员', NULL, NULL, '123', 1);

-- ----------------------------
-- Table structure for sys_materiel_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_materiel_file`;
CREATE TABLE `sys_materiel_file`  (
  `pk_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `materiel_id` int(0) NOT NULL COMMENT '产品编号',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存储文件名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '展示文件名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片url',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传人',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_materiel_file
-- ----------------------------
INSERT INTO `sys_materiel_file` VALUES (25, 5, '026bde50e8774834b4bcd05e9e2e15ff.jpg', '717760292880121856 (1).jpg', 'http://192.168.31.200/2020/11/026bde50e8774834b4bcd05e9e2e15ff.jpg', '/images/2020/11/', NULL, '2020-11-20 16:34:41', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (26, 5, 'c736664cb02b4af4b31852272771ceae.jpg', '717760292880121856 (2).jpg', 'http://192.168.31.200/2020/11/c736664cb02b4af4b31852272771ceae.jpg', '/images/2020/11/', NULL, '2020-11-20 16:34:42', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (27, 5, 'b3b43dbd496348b884057dd13df79e21.jpg', '717760292880121856 (3).jpg', 'http://192.168.31.200/2020/11/b3b43dbd496348b884057dd13df79e21.jpg', '/images/2020/11/', NULL, '2020-11-20 16:34:42', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (28, 5, '43672675fd744bd9a5115a2e59323633.jpg', '717760292880121856 (4).jpg', 'http://192.168.31.200/2020/11/43672675fd744bd9a5115a2e59323633.jpg', '/images/2020/11/', NULL, '2020-11-20 16:34:42', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (29, 4, '537015013b374551a935c92849ae5b7c.jpg', '717760292880121856.jpg', 'http://192.168.31.200/2020/11/537015013b374551a935c92849ae5b7c.jpg', '/images/2020/11/', NULL, '2020-11-20 16:35:03', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (30, 4, '8213a263d9da449f86bc876098be175b.jpg', '718035592478195712 (1).jpg', 'http://192.168.31.200/2020/11/8213a263d9da449f86bc876098be175b.jpg', '/images/2020/11/', NULL, '2020-11-20 16:35:04', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (31, 4, 'ba9c8050f0e64938a6babfd24b75ab9f.jpg', '718035592478195712.jpg', 'http://192.168.31.200/2020/11/ba9c8050f0e64938a6babfd24b75ab9f.jpg', '/images/2020/11/', NULL, '2020-11-20 16:35:04', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (36, 3, '4216954131d94795be29230a536282ce.jpg', '718035592478195712.jpg', 'http://192.168.31.200/2020/11/4216954131d94795be29230a536282ce.jpg', '/images/2020/11/', NULL, '2020-11-20 16:35:34', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (37, 3, 'c2d95576dee14bd3ad80d38816c76e4a.jpg', '718036121602228224.jpg', 'http://192.168.31.200/2020/11/c2d95576dee14bd3ad80d38816c76e4a.jpg', '/images/2020/11/', NULL, '2020-11-20 16:35:35', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (38, 3, 'edebb23d486047059aa5f0a31f6763bd.jpg', '718036397939752960.jpg', 'http://192.168.31.200/2020/11/edebb23d486047059aa5f0a31f6763bd.jpg', '/images/2020/11/', NULL, '2020-11-20 16:35:35', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (39, 2, '19209a5b38b444f1bd49909e339faddf.jpg', '718037314734915584.jpg', 'http://192.168.31.200/2020/11/19209a5b38b444f1bd49909e339faddf.jpg', '/images/2020/11/', NULL, '2020-11-20 16:35:49', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (40, 2, '1b20161384ed415199f41f8b0d75b6ff.jpg', '718037322188193792.jpg', 'http://192.168.31.200/2020/11/1b20161384ed415199f41f8b0d75b6ff.jpg', '/images/2020/11/', NULL, '2020-11-20 16:35:49', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (41, 2, '91bac465d06042feb341824aa5c05a75.png', '718049591865376768.png', 'http://192.168.31.200/2020/11/91bac465d06042feb341824aa5c05a75.png', '/images/2020/11/', NULL, '2020-11-20 16:35:49', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (42, 1, '63f2d18e8fa5460d95cb4c4c0ffa95f4.jpg', '718036829676240896.jpg', 'http://192.168.31.200/2020/11/63f2d18e8fa5460d95cb4c4c0ffa95f4.jpg', '/images/2020/11/', NULL, '2020-11-20 16:36:10', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (43, 1, '5b4050fde32d43f7a69532b144845b2a.jpg', '718037322188193792 (1).jpg', 'http://192.168.31.200/2020/11/5b4050fde32d43f7a69532b144845b2a.jpg', '/images/2020/11/', NULL, '2020-11-20 16:36:10', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (44, 1, 'b453c97d0d284ed2968105b30901e1b7.jpg', '718036840434630656.jpg', 'http://192.168.31.200/2020/11/b453c97d0d284ed2968105b30901e1b7.jpg', '/images/2020/11/', NULL, '2020-11-20 16:36:10', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (45, 1, 'd527f8f3509c4ebbaf8d54ab5987628c.jpg', '718037314734915584.jpg', 'http://192.168.31.200/2020/11/d527f8f3509c4ebbaf8d54ab5987628c.jpg', '/images/2020/11/', NULL, '2020-11-20 16:36:10', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (46, 6, '6e1ebc7053b5418c9cf52c11f658f58f.gif', 'avatar.gif', 'http://192.168.31.200/2020/11/6e1ebc7053b5418c9cf52c11f658f58f.gif', '/images/2020/11/', NULL, '2020-11-28 10:43:17', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (47, 6, '4b3f66e7e7164a949608b63d23666650.jpg', 'avatar.jpg', 'http://192.168.31.200/2020/11/4b3f66e7e7164a949608b63d23666650.jpg', '/images/2020/11/', NULL, '2020-11-28 10:43:17', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (48, 6, 'cee7033cc9ad48a9920e192b2ac0d07c.jpg', 'login-bg.jpg', 'http://192.168.31.200/2020/11/cee7033cc9ad48a9920e192b2ac0d07c.jpg', '/images/2020/11/', NULL, '2020-11-28 10:43:17', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (49, 6, 'bf74ead84dee44dba8c3bd97c750d662.png', 'logo.png', 'http://192.168.31.200/2020/11/bf74ead84dee44dba8c3bd97c750d662.png', '/images/2020/11/', NULL, '2020-11-28 10:43:17', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (50, 6, 'a226c7f813cc4ed3b6b6716397edef8e.jpg', 'starry.jpg', 'http://192.168.31.200/2020/11/a226c7f813cc4ed3b6b6716397edef8e.jpg', '/images/2020/11/', NULL, '2020-11-28 10:43:17', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (58, 7, 'ed8f42a45c504de38a85b38c99e47eee.jpg', '717760292880121856.jpg', 'http://192.168.31.200/2020/12/ed8f42a45c504de38a85b38c99e47eee.jpg', '/images/2020/12/', NULL, '2020-12-08 11:07:36', '超级管理员', NULL);
INSERT INTO `sys_materiel_file` VALUES (60, 7, 'c54ffe6da5544609ab0ae39f2e1b6c9b.jpg', '12331.jpg', 'http://192.168.31.200/2020/12/c54ffe6da5544609ab0ae39f2e1b6c9b.jpg', '/images/2020/12/', NULL, '2020-12-08 16:21:18', '超级管理员', NULL);

-- ----------------------------
-- Table structure for sys_materiel_model
-- ----------------------------
DROP TABLE IF EXISTS `sys_materiel_model`;
CREATE TABLE `sys_materiel_model`  (
  `pk_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `m_id` int(0) NOT NULL COMMENT '产品id',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '型号',
  `price` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '产品型号价格',
  PRIMARY KEY (`pk_id`, `m_id`, `model_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品型号关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_materiel_model
-- ----------------------------
INSERT INTO `sys_materiel_model` VALUES (1, 1, '大人', NULL);
INSERT INTO `sys_materiel_model` VALUES (2, 1, '小孩', NULL);
INSERT INTO `sys_materiel_model` VALUES (3, 1, '老人', NULL);
INSERT INTO `sys_materiel_model` VALUES (4, 2, '大号', NULL);
INSERT INTO `sys_materiel_model` VALUES (5, 2, '中号', NULL);
INSERT INTO `sys_materiel_model` VALUES (6, 2, '小号', NULL);
INSERT INTO `sys_materiel_model` VALUES (7, 3, '毛绒', NULL);
INSERT INTO `sys_materiel_model` VALUES (8, 3, '丝绸', NULL);
INSERT INTO `sys_materiel_model` VALUES (9, 3, '尼龙布', NULL);
INSERT INTO `sys_materiel_model` VALUES (10, 4, '大', NULL);
INSERT INTO `sys_materiel_model` VALUES (11, 4, '中', NULL);
INSERT INTO `sys_materiel_model` VALUES (12, 4, '小', NULL);
INSERT INTO `sys_materiel_model` VALUES (13, 5, '大', NULL);
INSERT INTO `sys_materiel_model` VALUES (14, 5, '中', NULL);
INSERT INTO `sys_materiel_model` VALUES (15, 5, '小', NULL);
INSERT INTO `sys_materiel_model` VALUES (16, 6, '大', 130.00);
INSERT INTO `sys_materiel_model` VALUES (17, 6, '中', 123.00);
INSERT INTO `sys_materiel_model` VALUES (18, 6, '小', 122.00);
INSERT INTO `sys_materiel_model` VALUES (29, 7, '1', 12.00);
INSERT INTO `sys_materiel_model` VALUES (30, 7, '2', 12.00);
INSERT INTO `sys_materiel_model` VALUES (31, 7, '3', 12.00);
INSERT INTO `sys_materiel_model` VALUES (32, 8, '321', 231321.00);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父菜单ID',
  `order_num` tinyint(0) NULL DEFAULT 1 COMMENT '显示顺序',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否为外链（0否 1是）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否隐藏路由，（0否 1是）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `permissions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70601 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (10000, '系统管理', 0, 1, '/system', '', '0', '0', 'M', '0', NULL, 'el-icon-setting', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10100, '角色管理', 10000, 1, '/system/role', '/system/role/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10101, '新增', 10100, 1, NULL, NULL, '0', '0', 'F', '0', 'system:role:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10102, '修改', 10100, 1, NULL, NULL, '0', '0', 'F', '0', 'system:role:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10103, '删除', 10100, 1, NULL, NULL, '0', '0', 'F', '0', 'system:role:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10104, '查看用户', 10100, 1, NULL, NULL, '0', '0', 'F', '0', 'system:role:user', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10105, '菜单权限', 10100, 1, NULL, NULL, '0', '0', 'F', '0', 'system:role:permission', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10106, '数据权限', 10100, 1, NULL, NULL, '0', '0', 'F', '0', 'system:role:dataSource', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10107, '列表', 10100, 1, NULL, NULL, '0', '0', 'F', '0', 'system:role:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10200, '部门管理', 10000, 2, '/system/dept', '/system/dept/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10201, '新增', 10200, 1, NULL, NULL, '0', '0', 'F', '0', 'system:dept:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10202, '修改', 10200, 1, NULL, NULL, '0', '0', 'F', '0', 'system:dept:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10203, '删除', 10200, 1, NULL, NULL, '0', '0', 'F', '0', 'system:dept:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10204, '查看用户', 10200, 1, NULL, NULL, '0', '0', 'F', '0', 'system:dept:user', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10205, '列表', 10200, 1, NULL, NULL, '0', '0', 'F', '0', 'system:dept:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10300, '用户管理', 10000, 3, '/system/user', '/system/user/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10301, '分配角色', 10300, 1, NULL, NULL, '0', '0', 'F', '0', 'system:user:role', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10302, '查看权限', 10300, 1, NULL, NULL, '0', '0', 'F', '0', 'system:user:permission', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10303, '重置密码', 10300, 1, NULL, NULL, '0', '0', 'F', '0', 'system:user:reset', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10304, '删除用户', 10300, 1, NULL, NULL, '0', '0', 'F', '0', 'system:user:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10305, '修改状态', 10300, 1, NULL, NULL, '0', '0', 'F', '0', 'system:user:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (10306, '列表', 10300, 1, NULL, NULL, '0', '0', 'F', '0', 'system:user:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20000, '基础档案', 0, 2, '/basis', '', '0', '0', 'M', '0', NULL, 'el-icon-s-help', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20100, '人员档案', 20000, 1, '/basis/personnel', '/basis/personnel/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20101, '新增', 20100, 1, NULL, NULL, '0', '0', 'F', '0', 'basis:personnel:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20102, '修改', 20100, 2, NULL, NULL, '0', '0', 'F', '0', 'basis:personnel:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20103, '删除', 20100, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:personnel:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20104, '列表', 20100, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:personnel:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20200, '产品类别', 20000, 2, '/basis/materielType', '/basis/materielType/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20201, '新增', 20200, 1, NULL, NULL, '0', '0', 'F', '0', 'basis:materielType:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20202, '修改', 20200, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:materielType:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20203, '删除', 20200, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:materielType:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20204, '列表', 20200, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:materielType:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20300, '产品档案', 20000, 3, '/basis/materiel', '/basis/materiel/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20301, '新增', 20300, 1, NULL, NULL, '0', '0', 'F', '0', 'basis:materiel:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20302, '修改', 20300, 2, NULL, NULL, '0', '0', 'F', '0', 'basis:materiel:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20303, '删除', 20300, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:materiel:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20304, '上传图纸', 20300, 4, NULL, NULL, '0', '0', 'F', '0', 'basis:materiel:upload', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20305, '列表', 20300, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:materiel:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20400, '仓库管理', 20000, 4, '/basis/warehouse', '/basis/warehouse/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20401, '新增', 20400, 1, NULL, NULL, '0', '0', 'F', '0', 'basis:warehouse:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20402, '修改', 20400, 2, NULL, NULL, '0', '0', 'F', '0', 'basis:warehouse:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20403, '删除', 20400, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:warehouse:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20404, '列表', 20400, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:warehouse:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20500, '基本单位管理', 20000, 5, '/basis/units', '/basis/units/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20501, '新增', 20500, 1, NULL, NULL, '0', '0', 'F', '0', 'basis:units:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20502, '修改', 20500, 2, NULL, NULL, '0', '0', 'F', '0', 'basis:units:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20503, '删除', 20500, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:units:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20504, '列表', 20500, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:units:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20600, '客户类别', 20000, 6, '/basis/clienteleType', '/basis/clienteleType/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20601, '新增', 20600, 1, NULL, NULL, '0', '0', 'F', '0', 'basis:clienteleType:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20602, '修改', 20600, 2, NULL, NULL, '0', '0', 'F', '0', 'basis:clienteleType:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20603, '删除', 20600, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:clienteleType:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20604, '列表', 20600, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:clienteleType:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20700, '客户档案', 20000, 7, '/basis/clientele', '/basis/clientele/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20701, '新增', 20700, 1, NULL, NULL, '0', '0', 'F', '0', 'basis:clientele:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20702, '修改', 20700, 2, NULL, NULL, '0', '0', 'F', '0', 'basis:clientele:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20703, '删除', 20700, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:clientele:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20704, '列表', 20700, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:clientele:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20710, '客户产品价', 20700, 7, '/basis/clienteleProduct', '/basis/clienteleProduct/index', '0', '0', 'F', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20711, '新增', 20710, 1, NULL, NULL, '0', '0', 'F', '0', 'basis:clienteleProduct:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20712, '修改', 20710, 2, NULL, NULL, '0', '0', 'F', '0', 'basis:clienteleProduct:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20713, '删除', 20710, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:clienteleProduct:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (20714, '列表', 20710, 3, NULL, NULL, '0', '0', 'F', '0', 'basis:clienteleProduct:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30000, '销售管理', 0, 3, '/sales', NULL, '0', '0', 'M', '0', NULL, 'el-icon-s-order', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30100, '销售报价', 30000, 1, '/sales/quotation', '/sales/quotation/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30101, '列表', 30100, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:quotation:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30110, '新增', 30100, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:quotation:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30120, '修改', 30100, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:quotation:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30130, '明细', 30100, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:quotation:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30140, '提交', 30100, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:quotation:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30150, '审核', 30100, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:quotation:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30160, '生成订单', 30100, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:quotation:order', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30170, '打印', 30100, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:quotation:print', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30200, '销售订单', 30000, 2, '/sales/order', '/sales/order/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30201, '列表', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30210, '新增', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30220, '修改', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30230, '明细', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30240, '发货收款明细', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:schedule', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30250, '审核', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30260, '发货', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:shipments', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30270, '打印', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:print', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30280, '关闭', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:close', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30290, '提交', 30200, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:order:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30300, '销售发货', 30000, 3, '/sales/shipments', '/sales/shipments/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30301, '列表', 30300, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:shipments:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30310, '新增', 30300, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:shipments:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30320, '修改', 30300, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:shipments:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30330, '明细', 30300, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:shipments:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30340, '提交', 30300, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:shipments:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30350, '审核', 30300, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:shipments:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30360, '打印', 30300, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:shipments:print', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30370, '特批', 30300, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:shipments:approve', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30400, '销售签回', 30000, 4, '/sales/signBack', 'sales/signBack/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30401, '列表', 30400, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:signback:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30420, '签收', 30400, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:signback:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30430, '明细', 30400, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:signback:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30450, '审核', 30400, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:signback:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30460, '打印', 30400, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:signback:print', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30500, '销售退货', 30000, 5, '/sales/returns', '/sales/returns/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30501, '列表', 30500, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:returns:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30510, '新增', 30500, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:returns:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30520, '修改', 30500, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:returns:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30530, '明细', 30500, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:returns:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30540, '提交', 30500, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:returns:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30550, '审核', 30500, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:returns:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (30560, '打印', 30500, 1, NULL, NULL, '0', '0', 'F', '0', 'sales:returns:print', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40000, '仓库管理', 0, 4, '/warehouse', NULL, '0', '0', 'M', '0', NULL, 'el-icon-coin', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40100, '初始化', 40000, 1, '/warehouse/init', '/warehouse/init/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40110, '新增', 40100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:init:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40120, '修改', 40100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:init:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40130, '删除', 40100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:init:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40140, '列表', 40100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:init:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40200, '产品入库', 40000, 2, '/warehouse/storage', '/warehouse/storage/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40201, '列表', 40200, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:storage:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40210, '新增', 40200, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:storage:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40220, '修改', 40200, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:storage:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40230, '明细', 40200, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:storage:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40240, '提交', 40200, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:storage:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40250, '审核', 40200, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:storage:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40260, '删除', 40200, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:storage:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40300, '销售出库', 40000, 3, '/warehouse/outbound', '/warehouse/outbound/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40301, '列表', 40300, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:outbound:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40320, '修改', 40300, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:outbound:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40330, '明细', 40300, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:outbound:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40350, '审核', 40300, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:outbound:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40400, '盘点', 40000, 4, '/warehouse/inventory', '/warehouse/inventory/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40401, '列表', 40400, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:inventory:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40410, '新增', 40400, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:inventory:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40420, '修改', 40400, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:inventory:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40430, '明细', 40400, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:inventory:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40440, '提交', 40400, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:inventory:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40450, '审核', 40400, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:inventory:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40500, '退货入库', 40000, 5, '/warehouse/returns', '/warehouse/returns/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40501, '列表', 40500, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:returns:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40520, '修改', 40500, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:returns:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40530, '明细', 40500, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:returns:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40540, '提交', 40500, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:returns:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40550, '审核', 40500, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:returns:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40600, '调整', 40000, 6, '/warehouse/adjustment', '/warehouse/adjustment/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40601, '列表', 40600, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:adjustment:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40610, '新增', 40600, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:adjustment:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40620, '修改', 40600, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:adjustment:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40630, '明细', 40600, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:adjustment:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40640, '提交', 40600, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:adjustment:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40650, '审核', 40600, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:adjustment:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40700, '报废', 40000, 7, '/warehouse/scrap', '/warehouse/scrap/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40701, '列表', 40700, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:scrap:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40710, '新增', 40700, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:scrap:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40720, '修改', 40700, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:scrap:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40730, '明细', 40700, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:scrap:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40740, '提交', 40700, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:scrap:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40750, '审核', 40700, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:scrap:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40800, '现存量(有价)', 40000, 8, '/warehouse/repertory/1', '/warehouse/repertory/index', '0', '0', 'C', '0', 'warehouse:repertory:list', 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (40900, '现存量(无价)', 40000, 9, '/warehouse/repertory/2', '/warehouse/repertory/index2', '0', '0', 'C', '0', 'warehouse:repertory:list2', 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (41000, '交易记录', 40000, 10, '/warehouse/transaction', '/warehouse/transaction/index', '0', '0', 'C', '1', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (41100, '其他出库', 40000, 3, '/warehouse/otherOutbound', '/warehouse/otherOutbound/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (41101, '列表', 41100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:otherOutbound:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (41110, '新增', 41100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:otherOutbound:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (41120, '修改', 41100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:otherOutbound:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (41130, '明细', 41100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:otherOutbound:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (41140, '提交', 41100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:otherOutbound:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (41150, '审核', 41100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:otherOutbound:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (41160, '删除', 41100, 1, NULL, NULL, '0', '0', 'F', '0', 'warehouse:otherOutbound:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50000, '财务管理', 0, 6, '/finance', NULL, '0', '0', 'M', '0', NULL, 'el-icon-s-data', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50100, '财务初始化', 50000, 1, '/finance/init', '/finance/init/index', '0', '0', 'C', '1', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50200, '应收款', 50000, 2, '/finance/receivable', '/finance/receivable/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50201, '列表', 50200, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receivable:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50210, '新增', 50200, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receivable:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50220, '修改', 50200, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receivable:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50230, '明细', 50200, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receivable:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50240, '提交', 50200, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receivable:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50250, '审核', 50200, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receivable:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50300, '收款', 50000, 3, '/finance/receipt', '/finance/receipt/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50301, '列表', 50300, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receipt:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50310, '新增', 50300, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receipt:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50320, '修改', 50300, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receipt:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50330, '明细', 50300, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receipt:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50340, '提交', 50300, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receipt:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50350, '审核', 50300, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:receipt:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50400, '对账单', 50000, 5, '/finance/statements', '/finance/statements/index', '0', '0', 'C', '1', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50500, '核销', 50000, 4, '/finance/writeoff', '/finance/writeoff/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50501, '列表', 50500, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:writeoff:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50510, '新增', 50500, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:writeoff:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50530, '明细', 50500, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:writeoff:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50550, '核销', 50500, 1, NULL, NULL, '0', '0', 'F', '0', 'finance:writeoff:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (50600, '应付款', 50000, 2, '/finance/payable', '/finance/payable/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (60000, '系统监控', 0, 6, '/monitor', '', '0', '0', 'M', '1', NULL, 'el-icon-discover', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (60100, '在线用户', 60000, 1, '/monitor/online', '/monitor/online/index', '0', '0', 'C', '1', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (60101, '列表', 60100, 1, NULL, NULL, '0', '0', 'F', '1', 'monitor:online:list', NULL, 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (60102, '下线', 60100, 2, NULL, NULL, '0', '0', 'F', '1', 'monitor:online:kickout', NULL, 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70000, '采购管理', 0, 5, '/purchase', NULL, '0', '0', 'M', '0', NULL, 'el-icon-box', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70100, '采购订单', 70000, 2, '/purchase/order', '/purchase/order/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70110, '新增', 70100, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:order:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70120, '修改', 70100, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:order:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70130, '明细', 70100, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:order:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70140, '提交', 70100, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:order:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70150, '审核', 70100, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:order:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70160, '删除', 70100, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:order:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70170, '列表', 70100, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:order:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70200, '供应商', 70000, 1, '/purchase/supplier', '/purchase/supplier/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70300, '采购退货', 70000, 5, '/purchase/returns', '/purchase/returns/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70400, '采购到货', 70000, 3, '/purchase/sign', '/purchase/sign/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70500, '采购入库', 40000, 7, '/purchase/storage', '/purchase/storage/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70510, '新增', 70500, 1, NULL, NULL, '0', '0', 'F', '1', 'purchase:storage:add', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70520, '修改', 70500, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:storage:edit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70530, '明细', 70500, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:storage:preview', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70540, '提交', 70500, 1, NULL, NULL, '0', '0', 'F', '1', 'purchase:storage:submit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70550, '审核', 70500, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:storage:audit', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70560, '删除', 70500, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:storage:delete', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70570, '列表', 70500, 1, NULL, NULL, '0', '0', 'F', '0', 'purchase:storage:list', NULL, 'admin', '2020-08-31 00:00:00', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (70600, '采购出库', 40000, 7, '/purchase/outbound', '/purchase/outbound/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(0) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(0) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(0) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
  `delivery_time` datetime(0) NULL DEFAULT NULL COMMENT '交货日期',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '客户类别id',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '开单人id',
  `pay_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '付款条件',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总计金额',
  `legal_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '法人',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `abbreviation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司简称',
  `certificate_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '统一社会信用号',
  `taxrate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '税率',
  `taxamount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '税额',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司地址',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `create_id` int(0) NULL DEFAULT NULL,
  `receipt_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计收款',
  `signback_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计签收金额（累计应收款金额）',
  `shipment_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计发货金额',
  `outbound_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计出库金额',
  `order_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单类型',
  `pay_staus` int(0) NULL DEFAULT 0 COMMENT '支付状态',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售订单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES (1, 'ON23182573573185536', '2020-10-28 00:00:00', '2020-11-18 12:00:21', 2, 'kh002', '客户002', 4, 1, NULL, '3', '', 268.38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 12:04:05', '超级管理员', '2020-11-18 12:06:47', 'admin', '2020-11-18 12:04:19', 1, NULL, 44.66, 67.20, 67.20, NULL, NULL);
INSERT INTO `sys_order` VALUES (2, 'ON23183857403502592', '2020-10-27 00:00:00', '2020-11-18 12:08:49', 2, 'kh002', '客户002', 4, 1, NULL, '3', '', 2749.18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 12:09:12', '超级管理员', '2020-11-18 12:14:59', 'admin', '2020-11-18 12:09:19', 1, NULL, 1920.38, 1920.38, 828.80, NULL, NULL);
INSERT INTO `sys_order` VALUES (3, 'ON23228575579475968', '2020-11-18 00:00:00', '2020-11-18 15:06:44', 2, 'kh002', '客户002', 4, 1, NULL, '3', '', 68.67, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 15:06:53', '超级管理员', '2020-11-18 15:28:30', 'admin', '2020-11-18 15:07:02', 1, NULL, NULL, 68.67, 68.67, NULL, NULL);
INSERT INTO `sys_order` VALUES (4, 'ON23528250865373184', '2020-11-20 00:00:00', '2020-11-19 10:57:29', 2, 'kh002', '客户002', 4, 1, NULL, '3', '', 1384.46, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-19 10:57:41', '超级管理员', '2020-11-19 10:57:48', 'admin', '2020-11-19 10:57:48', 1, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_order_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_sub`;
CREATE TABLE `sys_order_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(0) NOT NULL COMMENT '主表id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(0) NULL DEFAULT NULL COMMENT '数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `has_shipment_num` int(0) NULL DEFAULT 0 COMMENT '已发货数',
  `has_outbound_num` int(0) NULL DEFAULT 0 COMMENT '已出库数',
  `has_signback_num` int(0) NULL DEFAULT 0 COMMENT '已签收数',
  `has_return_num` int(0) NULL DEFAULT 0 COMMENT '退货数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售订单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order_sub
-- ----------------------------
INSERT INTO `sys_order_sub` VALUES (1, 1, 3, 'maojin', '毛巾', '洗脸、洗澡', '毛绒', NULL, NULL, 3, '条', 22.89, NULL, 0.00, NULL, 0, 0, 0, 0, '2020-11-18 12:04:06', '超级管理员', '2020-11-18 12:04:06', '超级管理员', 1);
INSERT INTO `sys_order_sub` VALUES (2, 1, 4, 'xilianp', '洗脸盆', '内用、不易破', '中', NULL, NULL, 4, '个', 44.66, 1, 44.66, NULL, 0, 0, 1, 0, '2020-11-18 12:04:06', '超级管理员', '2020-11-18 12:06:47', '超级管理员', 1);
INSERT INTO `sys_order_sub` VALUES (3, 1, 2, 'beiz', '杯子', '耐高温', '大号', NULL, NULL, 2, '杯', 33.60, 2, 67.20, NULL, 0, 0, 2, 0, '2020-11-18 12:04:06', '超级管理员', '2020-11-18 12:06:39', '超级管理员', 1);
INSERT INTO `sys_order_sub` VALUES (4, 1, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 2, 89.32, NULL, 0, 0, 2, 0, '2020-11-18 12:04:06', '超级管理员', '2020-11-18 12:06:43', '超级管理员', 1);
INSERT INTO `sys_order_sub` VALUES (5, 1, 2, 'beiz', '杯子', '耐高温', '小号', NULL, NULL, 2, '杯', 33.60, 2, 67.20, NULL, 0, 0, 2, 0, '2020-11-18 12:04:06', '超级管理员', '2020-11-18 12:06:35', '超级管理员', 1);
INSERT INTO `sys_order_sub` VALUES (6, 2, 1, 'yg', '牙膏', '好用、便宜', '大人', NULL, NULL, 1, '支', 25.90, 32, 828.80, NULL, 0, 0, 32, 0, '2020-11-18 12:09:12', '超级管理员', '2020-11-18 12:14:56', '超级管理员', 1);
INSERT INTO `sys_order_sub` VALUES (7, 2, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 43, 1920.38, NULL, 0, 0, 43, 0, '2020-11-18 12:09:12', '超级管理员', '2020-11-18 12:14:59', '超级管理员', 1);
INSERT INTO `sys_order_sub` VALUES (8, 3, 3, 'maojin', '毛巾', '洗脸、洗澡', '毛绒', NULL, NULL, 3, '条', 22.89, 3, 68.67, NULL, 0, 3, 0, 0, '2020-11-18 15:06:53', '超级管理员', '2020-11-18 15:28:30', '超级管理员', 1);
INSERT INTO `sys_order_sub` VALUES (9, 4, 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, 4, '个', 44.66, 31, 1384.46, NULL, 0, 0, 0, 0, '2020-11-19 10:57:41', '超级管理员', '2020-11-19 10:57:41', '超级管理员', 1);

-- ----------------------------
-- Table structure for sys_payable
-- ----------------------------
DROP TABLE IF EXISTS `sys_payable`;
CREATE TABLE `sys_payable`  (
  `payable_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `payable_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应付单号',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '应付金额',
  `finance_time` datetime(0) NULL DEFAULT NULL COMMENT '财务日期',
  `taxamount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '税额',
  `taxrate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '税率',
  `invoice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票号',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单号',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `returns_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '退货单号',
  `returns_time` datetime(0) NULL DEFAULT NULL COMMENT '退货日期',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `source_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '来源类型',
  `has_verifica_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '已核销金额',
  PRIMARY KEY (`payable_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '财务应付款主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_purchase_order`;
CREATE TABLE `sys_purchase_order`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `supplier_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `supplier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '开单人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开单人名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `total_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '总计金额',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`order_id`, `order_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购订单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_purchase_order
-- ----------------------------
INSERT INTO `sys_purchase_order` VALUES (2, 'ON28688028797227008', '2020-12-03 16:40:38', 2, '1', '1', 1, '超级管理员', '3', '', 4957.26, '1', NULL, '1', NULL, '111111111111111111111111', 1, '超级管理员', '2020-12-03 16:40:48', '超级管理员', '2020-12-08 16:34:09', '超级管理员', '2020-12-08 16:34:09');
INSERT INTO `sys_purchase_order` VALUES (6, 'ON28689217345560576', '2020-12-03 16:45:12', 4, '2313', '123', 1, '超级管理员', '3', '2222', 4805.79, '132', NULL, '1231', NULL, NULL, 1, '超级管理员', '2020-12-03 00:00:00', '超级管理员', '2020-12-07 11:35:40', '超级管理员', '2020-12-07 11:35:40');
INSERT INTO `sys_purchase_order` VALUES (7, 'ON30523989646651392', '2020-12-08 00:00:00', 8, '2', '客户002', 1, '超级管理员', '4', '', 132.00, NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-08 00:00:00', '超级管理员', '2020-12-08 18:30:27', '超级管理员', '2020-12-08 18:30:27');
INSERT INTO `sys_purchase_order` VALUES (9, 'ON30750748015861760', '2020-12-09 00:00:00', 8, '2', '客户002', 1, '超级管理员', '3', '', 6693.06, NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-09 00:00:00', '超级管理员', '2020-12-09 09:57:03', '超级管理员', '2020-12-09 09:57:03');
INSERT INTO `sys_purchase_order` VALUES (10, 'ON30766610977849344', '2020-12-09 00:00:00', 8, '2', '客户002', 1, '超级管理员', '0', '', 0.00, NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-09 10:20:21', NULL, NULL, NULL, NULL);
INSERT INTO `sys_purchase_order` VALUES (11, 'ON30767361661587456', '2020-12-09 00:00:00', 7, '1', '客户001', 1, '超级管理员', '0', '', 2675.88, NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-09 10:23:20', NULL, NULL, NULL, NULL);
INSERT INTO `sys_purchase_order` VALUES (12, 'ON30767402627354624', '2020-12-09 00:00:00', 7, '1', '客户001', 1, '超级管理员', '0', '', 1720.64, NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-09 00:00:00', '超级管理员', '2020-12-09 10:30:53', NULL, NULL);
INSERT INTO `sys_purchase_order` VALUES (13, 'ON31197757364981760', '2020-12-10 00:00:00', 7, '1', '客户001', 1, '超级管理员', '3', '', 9316.23, NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-10 14:53:34', '超级管理员', '2020-12-10 14:53:39', '超级管理员', '2020-12-10 14:53:39');
INSERT INTO `sys_purchase_order` VALUES (14, 'ON31515103007055872', '2020-12-11 00:00:00', 7, '1', '客户001', 1, '超级管理员', '3', '', 23529.78, NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-11 11:54:35', '超级管理员', '2020-12-11 11:54:44', '超级管理员', '2020-12-11 11:54:44');
INSERT INTO `sys_purchase_order` VALUES (15, 'ON31563096657219584', '2020-12-11 00:00:00', 7, '1', '客户001', 1, '超级管理员', '3', '', 1485.00, NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-11 15:05:18', '超级管理员', '2020-12-11 15:05:26', '超级管理员', '2020-12-11 15:05:26');

-- ----------------------------
-- Table structure for sys_purchase_order_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_purchase_order_sub`;
CREATE TABLE `sys_purchase_order_sub`  (
  `order_sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(0) NOT NULL COMMENT '主表id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '单价',
  `number` int(0) NULL DEFAULT 0 COMMENT '数量',
  `total_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '金额',
  `has_sign_num` int(0) NULL DEFAULT 0 COMMENT '累计签收数',
  `has_storage_num` int(0) NULL DEFAULT 0 COMMENT '累计入库数',
  `has_return_num` int(0) NULL DEFAULT 0 COMMENT '累计退货数',
  PRIMARY KEY (`order_sub_id`, `order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购订单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_purchase_order_sub
-- ----------------------------
INSERT INTO `sys_purchase_order_sub` VALUES (3, 2, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 111, 4957.26, NULL, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (7, 6, 6, 'yf', '衣服', '123434512512', '小', NULL, NULL, 3, '条', 123.00, 22, 2706.00, NULL, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (9, 6, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '中', NULL, NULL, 4, '个', 99.99, 21, 2099.79, NULL, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (10, 7, 7, 'asd', '安达市', '阿萨德', '1', NULL, NULL, 3, '条', 12.00, 11, 132.00, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (13, 9, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '小', NULL, NULL, 4, '个', 99.99, 12, 1199.88, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (14, 9, 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, 4, '个', 44.66, 123, 5493.18, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (15, 10, 7, 'asd', '安达市', '阿萨德', '2', NULL, NULL, 3, '条', 12.00, 0, 0.00, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (16, 10, 6, 'yf', '衣服', '123434512512', '中', NULL, NULL, 3, '条', 123.00, 0, 0.00, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (17, 11, 6, 'yf', '衣服', '123434512512', '大', NULL, NULL, 3, '条', 123.00, 12, 1476.00, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (18, 11, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '大', NULL, NULL, 4, '个', 99.99, 12, 1199.88, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (19, 12, 6, 'yf', '衣服', '123434512512', '大', NULL, NULL, 3, '条', 123.00, 12, 1476.00, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (24, 12, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '小', NULL, NULL, 4, '个', 99.99, 2, 199.98, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (25, 12, 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, 4, '个', 44.66, 1, 44.66, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (26, 13, 6, 'yf', '衣服', '123434512512', '小', NULL, NULL, 3, '条', 123.00, 11, 1353.00, 0, 11, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (27, 13, 7, 'asd', '安达市', '阿萨德', '1', NULL, NULL, 3, '条', 12.00, 22, 264.00, 44, -22, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (28, 13, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '小', NULL, NULL, 4, '个', 99.99, 33, 3299.67, 0, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (29, 13, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '中', NULL, NULL, 4, '个', 99.99, 44, 4399.56, 22, 0, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (30, 14, 7, 'asd', '安达市', '阿萨德', '3', NULL, NULL, 3, '条', 12.00, 111, 1332.00, 44, -11, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (31, 14, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '大', NULL, NULL, 4, '个', 99.99, 222, 22197.78, 88, -22, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (32, 15, 7, 'asd', '安达市', '阿萨德', '1', NULL, NULL, 3, '条', 12.00, 11, 132.00, 0, 11, 0);
INSERT INTO `sys_purchase_order_sub` VALUES (33, 15, 6, 'yf', '衣服', '123434512512', '小', NULL, NULL, 3, '条', 123.00, 11, 1353.00, 0, 11, 0);

-- ----------------------------
-- Table structure for sys_purchase_returns
-- ----------------------------
DROP TABLE IF EXISTS `sys_purchase_returns`;
CREATE TABLE `sys_purchase_returns`  (
  `returns_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `returns_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '退货单号',
  `returns_time` datetime(0) NULL DEFAULT NULL COMMENT '退货日期',
  `order_id` int(0) NOT NULL COMMENT '订单id',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单单号',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `supplier_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `supplier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '开单人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开单人名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `warehouse_id` int(0) NULL DEFAULT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `outbound_time` datetime(0) NULL DEFAULT NULL COMMENT '出库时间',
  `outbound_status` int(0) NULL DEFAULT 0 COMMENT '出库状态',
  `audit_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出库审核状态',
  PRIMARY KEY (`returns_id`, `returns_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购退货主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_purchase_returns
-- ----------------------------
INSERT INTO `sys_purchase_returns` VALUES (1, 'RN31504446588751872', '2020-12-11 00:00:00', 13, 'ON31197757364981760', '2020-12-10 00:00:00', 7, '1', '客户001', 1, '超级管理员', '3', '', NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-11 00:00:00', '超级管理员', '2020-12-11 11:31:27', '超级管理员', '2020-12-11 00:00:00', 1, '1', '1', '2020-12-11 00:00:00', 1, '1');
INSERT INTO `sys_purchase_returns` VALUES (2, 'RN31564008595378176', '2020-12-11 00:00:00', 15, 'ON31563096657219584', '2020-12-11 00:00:00', 7, '1', '客户001', 1, '超级管理员', '3', '2222', NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-11 00:00:00', '超级管理员', '2020-12-11 15:21:38', '超级管理员', '2020-12-11 15:21:38', 1, '1', '1', '2020-12-11 00:00:00', 0, '4');

-- ----------------------------
-- Table structure for sys_purchase_returns_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_purchase_returns_sub`;
CREATE TABLE `sys_purchase_returns_sub`  (
  `returns_sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `returns_id` int(0) NOT NULL COMMENT '主键',
  `order_sub_id` bigint(0) NOT NULL COMMENT '主键',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '单价',
  `number` int(0) NULL DEFAULT 0 COMMENT '数量',
  `returns_num` int(0) NULL DEFAULT 0 COMMENT '本次退货签收数',
  `outbound_num` int(0) NULL DEFAULT 0 COMMENT '本次退货出库数',
  `has_returns_num` int(0) NULL DEFAULT 0 COMMENT '已退货数',
  PRIMARY KEY (`returns_sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购退货单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_purchase_returns_sub
-- ----------------------------
INSERT INTO `sys_purchase_returns_sub` VALUES (1, 1, 29, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '中', NULL, NULL, 4, '个', 99.99, 44, 30, 10, 0);
INSERT INTO `sys_purchase_returns_sub` VALUES (2, 2, 32, 7, 'asd', '安达市', '阿萨德', '1', NULL, NULL, 3, '条', 12.00, 11, 11, 11, 0);

-- ----------------------------
-- Table structure for sys_purchase_sign
-- ----------------------------
DROP TABLE IF EXISTS `sys_purchase_sign`;
CREATE TABLE `sys_purchase_sign`  (
  `sign_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sign_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '到货单号',
  `sign_time` datetime(0) NULL DEFAULT NULL COMMENT '到货日期',
  `order_id` int(0) NOT NULL COMMENT '订单id',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单单号',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `supplier_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `supplier_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `supplier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '开单人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开单人名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `warehouse_id` int(0) NULL DEFAULT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `storage_time` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `storage_status` int(0) NULL DEFAULT 0 COMMENT '入库状态',
  `audit_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入库审核状态',
  PRIMARY KEY (`sign_id`, `sign_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购到货主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_purchase_sign
-- ----------------------------
INSERT INTO `sys_purchase_sign` VALUES (8, 'SN30769921336262656', '2020-12-09 00:00:00', 0, 'ON28689217345560576', '2020-12-03 00:00:00', 0, '2313', '123', 1, '超级管理员', '3', '2222', '132', NULL, '1231', NULL, NULL, 1, '超级管理员', '2020-12-09 00:00:00', '超级管理员', '2020-12-09 16:06:03', NULL, NULL, 1, '1', '1', '2020-12-09 00:00:00', 1, '0');
INSERT INTO `sys_purchase_sign` VALUES (9, 'SN30774322562056192', '2020-12-09 00:00:00', 0, 'ON28688028797227008', '2020-12-03 00:00:00', 2, '1', '1', 1, '超级管理员', '0', '', '1', NULL, '1', NULL, '111111111111111111111111', 1, '超级管理员', '2020-12-09 10:51:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0');
INSERT INTO `sys_purchase_sign` VALUES (10, 'SN30774335803473920', '2020-12-09 00:00:00', 0, 'ON28688028797227008', '2020-12-03 00:00:00', 2, '1', '1', 1, '超级管理员', '0', '', '1', NULL, '1', NULL, '111111111111111111111111', 1, '超级管理员', '2020-12-09 00:00:00', '超级管理员', '2020-12-09 10:55:09', NULL, NULL, NULL, NULL, NULL, NULL, 0, '0');
INSERT INTO `sys_purchase_sign` VALUES (11, 'SN30775614252498944', '2020-12-09 00:00:00', 0, 'ON30750748015861760', '2020-12-09 00:00:00', 8, '2', '客户002', 1, '超级管理员', '1', '', NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-09 00:00:00', '超级管理员', '2020-12-09 10:57:16', NULL, NULL, NULL, NULL, NULL, NULL, 0, '0');
INSERT INTO `sys_purchase_sign` VALUES (12, 'SN30788609879818240', '2020-12-09 00:00:00', 0, 'ON30750748015861760', '2020-12-09 00:00:00', 8, '2', '客户002', 1, '超级管理员', '3', '', NULL, '', NULL, NULL, '213213', 1, '超级管理员', '2020-12-09 00:00:00', '超级管理员', '2020-12-10 11:44:13', '超级管理员', '2020-12-10 11:44:13', 2, '2', '2', '2020-12-10 00:00:00', 1, '4');
INSERT INTO `sys_purchase_sign` VALUES (13, 'SN31198361793761280', '2020-12-10 00:00:00', 13, 'ON31197757364981760', '2020-12-10 00:00:00', 7, '1', '客户001', 1, '超级管理员', '3', '', NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-10 00:00:00', '超级管理员', '2020-12-10 14:59:53', '超级管理员', '2020-12-10 14:59:53', 1, '1', '1', '2020-12-10 00:00:00', 1, '3');
INSERT INTO `sys_purchase_sign` VALUES (14, 'SN31209466209202176', '2020-12-10 00:00:00', 13, 'ON31197757364981760', '2020-12-10 00:00:00', 7, '1', '客户001', 1, '超级管理员', '3', '', NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-10 00:00:00', '超级管理员', '2020-12-10 15:41:54', '超级管理员', '2020-12-10 15:41:54', 1, '1', '1', '2020-12-10 00:00:00', 1, '3');
INSERT INTO `sys_purchase_sign` VALUES (15, 'SN31513139179397120', '2020-12-11 00:00:00', 13, 'ON31197757364981760', '2020-12-10 00:00:00', 7, '1', '客户001', 1, '超级管理员', '4', '', NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-11 00:00:00', '超级管理员', '2020-12-11 11:48:18', '超级管理员', '2020-12-11 11:48:18', 1, '1', '1', '2020-12-11 00:00:00', 0, '1');
INSERT INTO `sys_purchase_sign` VALUES (16, 'SN31515187211902976', '2020-12-11 00:00:00', 14, 'ON31515103007055872', '2020-12-11 00:00:00', 7, '1', '客户001', 1, '超级管理员', '3', '', NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-11 00:00:00', '超级管理员', '2020-12-11 14:55:38', '超级管理员', '2020-12-11 14:55:38', 1, '1', '1', '2020-12-11 00:00:00', 1, '3');
INSERT INTO `sys_purchase_sign` VALUES (17, 'SN31563166840508416', '2020-12-11 00:00:00', 15, 'ON31563096657219584', '2020-12-11 00:00:00', 7, '1', '客户001', 1, '超级管理员', '3', '', NULL, NULL, NULL, NULL, NULL, 1, '超级管理员', '2020-12-11 00:00:00', '超级管理员', '2020-12-11 15:06:40', '超级管理员', '2020-12-11 15:06:40', 1, '1', '1', '2020-12-11 00:00:00', 1, '3');

-- ----------------------------
-- Table structure for sys_purchase_sign_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_purchase_sign_sub`;
CREATE TABLE `sys_purchase_sign_sub`  (
  `sign_sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sign_id` int(0) NOT NULL COMMENT '主键',
  `order_sub_id` bigint(0) NOT NULL COMMENT '主键',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '单价',
  `number` int(0) NULL DEFAULT 0 COMMENT '数量',
  `sign_num` int(0) NULL DEFAULT 0 COMMENT '本次到货签收数',
  `storage_num` int(0) NULL DEFAULT 0 COMMENT '本次到货入库数',
  `has_sign_num` int(0) NULL DEFAULT 0 COMMENT '已到货签收数',
  PRIMARY KEY (`sign_sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '采购到货单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_purchase_sign_sub
-- ----------------------------
INSERT INTO `sys_purchase_sign_sub` VALUES (14, 8, 7, 6, 'yf', '衣服', '123434512512', '小', NULL, NULL, 3, '条', 123.00, 22, 1, 1, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (15, 9, 3, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 111, 22, 0, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (16, 10, 3, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 111, 11, 0, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (18, 11, 14, 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, 4, '个', 44.66, 123, 22, 0, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (19, 12, 13, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '小', NULL, NULL, 4, '个', 99.99, 12, 12, 12, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (21, 13, 26, 6, 'yf', '衣服', '123434512512', '小', NULL, NULL, 3, '条', 123.00, 11, 11, 11, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (22, 14, 29, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '中', NULL, NULL, 4, '个', 99.99, 44, 22, 0, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (23, 15, 27, 7, 'asd', '安达市', '阿萨德', '1', NULL, NULL, 3, '条', 12.00, 22, 22, 22, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (24, 16, 30, 7, 'asd', '安达市', '阿萨德', '3', NULL, NULL, 3, '条', 12.00, 111, 11, 11, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (25, 16, 31, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '大', NULL, NULL, 4, '个', 99.99, 222, 22, 22, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (26, 17, 32, 7, 'asd', '安达市', '阿萨德', '1', NULL, NULL, 3, '条', 12.00, 11, 11, 11, 0);
INSERT INTO `sys_purchase_sign_sub` VALUES (27, 17, 33, 6, 'yf', '衣服', '123434512512', '小', NULL, NULL, 3, '条', 123.00, 11, 11, 11, 0);

-- ----------------------------
-- Table structure for sys_quotation
-- ----------------------------
DROP TABLE IF EXISTS `sys_quotation`;
CREATE TABLE `sys_quotation`  (
  `quotation_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quotation_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报价单号',
  `quotation_time` datetime(0) NULL DEFAULT NULL COMMENT '报价日期',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '客户类别id',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '报价人id',
  `pay_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '付款条件',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总计金额',
  `effective_time` datetime(0) NULL DEFAULT NULL COMMENT '有效日期',
  `legal_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '法人',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `abbreviation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司简称',
  `certificate_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '统一社会信用号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司地址',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`quotation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售报价主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_quotation_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_quotation_sub`;
CREATE TABLE `sys_quotation_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quotation_id` int(0) NOT NULL COMMENT '主表id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(0) NULL DEFAULT NULL COMMENT '数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '产品类别id',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售报价子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_receipt
-- ----------------------------
DROP TABLE IF EXISTS `sys_receipt`;
CREATE TABLE `sys_receipt`  (
  `receipt_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receipt_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收款单号',
  `receipt_time` datetime(0) NULL DEFAULT NULL COMMENT '收款日期',
  `receipt_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '收款金额',
  `receipt_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收款账号',
  `payment_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收款方式',
  `payment_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '付款账号',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '业务员id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务员id',
  `personnel_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务员id',
  `dept_id` int(0) NULL DEFAULT NULL COMMENT '业务部门id',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务部门名称',
  `dept_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务部门编码',
  `has_verifica_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '已核销金额',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`receipt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '财务收款表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_receipt
-- ----------------------------
INSERT INTO `sys_receipt` VALUES (1, 'CN23183510333235200', '2020-11-18 00:00:00', 500.00, NULL, '0', NULL, '3', 2, 'kh002', '客户002', NULL, NULL, NULL, NULL, NULL, NULL, 500.00, 1, '超级管理员', '2020-11-18 12:07:49', '超级管理员', '2020-11-18 12:15:33', 'admin', '2020-11-18 12:08:19', NULL);
INSERT INTO `sys_receipt` VALUES (2, 'CN23183540905517056', '2020-11-18 00:00:00', 100.00, NULL, '0', NULL, '3', 2, 'kh002', '客户002', NULL, NULL, NULL, NULL, NULL, NULL, 100.00, 1, '超级管理员', '2020-11-18 12:07:56', '超级管理员', '2020-11-18 12:15:33', 'admin', '2020-11-18 12:08:15', NULL);
INSERT INTO `sys_receipt` VALUES (3, 'CN23183570336948224', '2020-11-18 00:00:00', 1000.00, NULL, '0', NULL, '3', 2, 'kh002', '客户002', NULL, NULL, NULL, NULL, NULL, NULL, 1000.00, 1, '超级管理员', '2020-11-18 12:08:03', '超级管理员', '2020-11-18 12:15:33', 'admin', '2020-11-18 12:08:11', NULL);

-- ----------------------------
-- Table structure for sys_receivable
-- ----------------------------
DROP TABLE IF EXISTS `sys_receivable`;
CREATE TABLE `sys_receivable`  (
  `receivable_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receivable_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应收单号',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '应收金额',
  `finance_time` datetime(0) NULL DEFAULT NULL COMMENT '财务日期',
  `taxamount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '税额',
  `taxrate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '税率',
  `invoice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发票号',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单号',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `shipments_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发货单号',
  `shipments_time` datetime(0) NULL DEFAULT NULL COMMENT '发货日期',
  `signback_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '签收单号',
  `signback_time` datetime(0) NULL DEFAULT NULL COMMENT '签收日期',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `source_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '来源类型',
  `has_verifica_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '已核销金额',
  PRIMARY KEY (`receivable_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '财务应收款主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_receivable
-- ----------------------------
INSERT INTO `sys_receivable` VALUES (1, 'AR23183200445472768', 2, 'kh002', '客户002', '3', 67.20, '2020-11-18 12:06:35', NULL, NULL, NULL, 'ON23182573573185536', '2020-11-18 00:00:00', 'DN23182680012038144', NULL, 'SN23183088520470528', '2020-11-18 12:06:18', 1, '超级管理员', '2020-11-18 12:06:35', '超级管理员', '2020-11-18 12:15:33', 'admin', '2020-11-18 12:07:29', '0', 67.20);
INSERT INTO `sys_receivable` VALUES (2, 'AR23183218808135680', 2, 'kh002', '客户002', '3', 67.20, '2020-11-18 12:06:39', NULL, NULL, NULL, 'ON23182573573185536', '2020-11-18 00:00:00', 'DN23182742314229760', NULL, 'SN23183077107769344', '2020-11-18 12:06:21', 1, '超级管理员', '2020-11-18 12:06:39', '超级管理员', '2020-11-18 12:15:33', 'admin', '2020-11-18 12:07:25', '0', 67.20);
INSERT INTO `sys_receivable` VALUES (3, 'AR23183232938745856', 2, 'kh002', '客户002', '3', 89.32, '2020-11-18 12:06:43', NULL, NULL, NULL, 'ON23182573573185536', '2020-11-18 00:00:00', 'DN23182712794718208', NULL, 'SN23183045604352000', '2020-11-18 12:06:25', 1, '超级管理员', '2020-11-18 12:06:43', '超级管理员', '2020-11-18 12:15:33', 'admin', '2020-11-18 12:07:22', '0', 89.32);
INSERT INTO `sys_receivable` VALUES (4, 'AR23183249292337152', 2, 'kh002', '客户002', '3', 44.66, '2020-11-18 12:06:47', NULL, NULL, NULL, 'ON23182573573185536', '2020-11-18 00:00:00', 'DN23182780859883520', NULL, 'SN23183033365372928', '2020-11-18 12:06:31', 1, '超级管理员', '2020-11-18 12:06:47', '超级管理员', '2020-11-18 12:15:33', 'admin', '2020-11-18 12:07:19', '0', 44.66);
INSERT INTO `sys_receivable` VALUES (5, 'AR23185301238128640', 2, 'kh002', '客户002', '3', 828.80, '2020-11-18 12:14:56', NULL, NULL, NULL, 'ON23183857403502592', '2020-11-18 00:00:00', 'DN23183930975789056', NULL, 'SN23185238239682560', '2020-11-18 12:14:47', 1, '超级管理员', '2020-11-18 12:14:56', '超级管理员', '2020-11-18 12:15:33', 'admin', '2020-11-18 12:15:11', '0', 828.80);
INSERT INTO `sys_receivable` VALUES (6, 'AR23185316312457216', 2, 'kh002', '客户002', '3', 1920.38, '2020-11-18 12:14:59', NULL, NULL, NULL, 'ON23183857403502592', '2020-11-18 00:00:00', 'DN23183951481741312', NULL, 'SN23185223454760960', '2020-11-18 12:14:50', 1, '超级管理员', '2020-11-18 12:14:59', '超级管理员', '2020-11-18 12:15:33', 'admin', '2020-11-18 12:15:08', '0', 502.82);

-- ----------------------------
-- Table structure for sys_repertory
-- ----------------------------
DROP TABLE IF EXISTS `sys_repertory`;
CREATE TABLE `sys_repertory`  (
  `pk_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(0) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `materiel_id` int(0) NOT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(0) NULL DEFAULT NULL COMMENT '现存量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品现存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_repertory
-- ----------------------------
INSERT INTO `sys_repertory` VALUES (1, 1, '1', '1', 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 100, 3438.82, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (2, 1, '1', '1', 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, 4, '个', 44.66, 100, 5448.52, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (3, 1, '1', '1', 4, 'xilianp', '洗脸盆', '内用、不易破', '中', NULL, NULL, 4, '个', 44.66, 100, 5403.86, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (4, 1, '1', '1', 3, 'maojin', '毛巾', '洗脸、洗澡', '毛绒', NULL, NULL, 3, '条', 22.89, 100, 2792.58, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (5, 1, '1', '1', 3, 'maojin', '毛巾', '洗脸、洗澡', '丝绸', NULL, NULL, 3, '条', 22.89, 100, 2792.58, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (6, 1, '1', '1', 3, 'maojin', '毛巾', '洗脸、洗澡', '尼龙布', NULL, NULL, 3, '条', 22.89, 100, 2792.58, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (7, 1, '1', '1', 2, 'beiz', '杯子', '耐高温', '大号', NULL, NULL, 2, '杯', 33.60, 100, 4032.00, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (8, 1, '1', '1', 2, 'beiz', '杯子', '耐高温', '中号', NULL, NULL, 2, '杯', 33.60, 100, 4099.20, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (9, 1, '1', '1', 2, 'beiz', '杯子', '耐高温', '小号', NULL, NULL, 2, '杯', 33.60, 100, 4032.00, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (10, 1, '1', '1', 1, 'yg', '牙膏', '好用、便宜', '大人', NULL, NULL, 1, '支', 25.90, 100, 2331.00, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (11, 1, '1', '1', 1, 'yg', '牙膏', '好用、便宜', '小孩', NULL, NULL, 1, '支', 25.90, 100, 3159.80, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (12, 1, '1', '1', 1, 'yg', '牙膏', '好用、便宜', '老人', NULL, NULL, 1, '支', 25.90, 100, 3159.80, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_repertory` VALUES (13, 2, '2', '2', 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, 4, '个', 44.66, 100, 14335.86, '超级管理员', '2020-12-03 14:54:52', NULL, NULL, 1);
INSERT INTO `sys_repertory` VALUES (14, 2, '2', '2', 6, 'yf', '衣服', '123434512512', '小', NULL, NULL, 3, '条', 123.00, 100, 26199.00, '超级管理员', '2020-12-03 14:54:52', NULL, NULL, 1);
INSERT INTO `sys_repertory` VALUES (15, 2, '2', '2', 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '大', NULL, NULL, 4, '个', 99.99, 100, 23097.69, '超级管理员', '2020-12-03 14:54:52', NULL, NULL, 1);
INSERT INTO `sys_repertory` VALUES (16, 2, '2', '2', 3, 'maojin', '毛巾', '洗脸、洗澡', '毛绒', NULL, NULL, 3, '条', 22.89, 100, 4875.57, '超级管理员', '2020-12-03 14:54:52', NULL, NULL, 1);
INSERT INTO `sys_repertory` VALUES (19, 1, '1', '1', 6, 'yf', '衣服', '123434512512', '小', NULL, NULL, 3, '条', 123.00, 22, 2706.00, '超级管理员', '2020-12-10 14:59:53', NULL, NULL, 1);
INSERT INTO `sys_repertory` VALUES (23, 1, '1', '1', 7, 'asd', '安达市', '阿萨德', '3', NULL, NULL, 3, '条', 12.00, 11, 132.00, '超级管理员', '2020-12-11 14:55:38', NULL, NULL, 1);
INSERT INTO `sys_repertory` VALUES (24, 1, '1', '1', 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '大', NULL, NULL, 4, '个', 99.99, 22, 2199.78, '超级管理员', '2020-12-11 14:55:38', NULL, NULL, 1);
INSERT INTO `sys_repertory` VALUES (27, 1, '1', '1', 7, 'asd', '安达市', '阿萨德', '1', NULL, NULL, 3, '条', 12.00, 11, 132.00, '超级管理员', '2020-12-11 15:21:38', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_returns
-- ----------------------------
DROP TABLE IF EXISTS `sys_returns`;
CREATE TABLE `sys_returns`  (
  `returns_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `returns_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '退货单号',
  `order_id` int(0) NOT NULL COMMENT '订单id',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单单号',
  `returns_time` datetime(0) NULL DEFAULT NULL COMMENT '退货日期',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '客户类别id',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '开单人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开单人名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司地址',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(0) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '退货总金额',
  `warehouse_id` int(0) NULL DEFAULT NULL COMMENT '仓库id',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `audit_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '入库审批状态',
  `storage_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '入库状态',
  `storage_id` int(0) NULL DEFAULT NULL COMMENT '入库人id',
  `storage_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入库人',
  `storage_time` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`returns_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售退货主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_returns_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_returns_sub`;
CREATE TABLE `sys_returns_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `returns_id` int(0) NOT NULL COMMENT '主表id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(0) NULL DEFAULT NULL COMMENT '订购数量',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `has_shipment_num` int(0) NULL DEFAULT 0 COMMENT '已发货数量',
  `has_outbound_num` int(0) NULL DEFAULT 0 COMMENT '已出库数量',
  `has_signback_num` int(0) NULL DEFAULT 0 COMMENT '已签收数量',
  `has_returns_num` int(0) NULL DEFAULT 0 COMMENT '已退货数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '退货金额',
  `reality_num` int(0) NULL DEFAULT 0 COMMENT '实际入库数量',
  `returns_num` int(0) NULL DEFAULT 0 COMMENT '本次退货数量',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `order_sub_id` bigint(0) NULL DEFAULT NULL COMMENT '订单子表id',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售退货子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_num` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编码',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '1', '1', NULL, '0', '超级管理员', '2020-11-21 13:03:17', 'ROOT', '2020-12-04 09:55:05', '1324444', 1);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `dept_id` int(0) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `menu_id` int(0) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 60000);
INSERT INTO `sys_role_menu` VALUES (1, 60100);
INSERT INTO `sys_role_menu` VALUES (1, 60101);
INSERT INTO `sys_role_menu` VALUES (1, 60102);

-- ----------------------------
-- Table structure for sys_scrap
-- ----------------------------
DROP TABLE IF EXISTS `sys_scrap`;
CREATE TABLE `sys_scrap`  (
  `scrap_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(0) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `total_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '报废总金额',
  `scrap_time` datetime(0) NULL DEFAULT NULL COMMENT '报废时间',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报废负责人',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '报废人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`scrap_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报废单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_scrap_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_scrap_sub`;
CREATE TABLE `sys_scrap_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scrap_id` int(0) NOT NULL COMMENT '主表id',
  `warehouse_id` int(0) NULL DEFAULT NULL COMMENT '仓库id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `scrap_num` int(0) NULL DEFAULT 0 COMMENT '报废数',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报废原因',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `number` int(0) NULL DEFAULT NULL COMMENT '现存数量',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报废单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_shipments
-- ----------------------------
DROP TABLE IF EXISTS `sys_shipments`;
CREATE TABLE `sys_shipments`  (
  `shipments_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shipments_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发货单号',
  `order_id` int(0) NOT NULL COMMENT '订单id',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单单号',
  `shipments_time` datetime(0) NULL DEFAULT NULL COMMENT '发货日期',
  `outbound_time` datetime(0) NULL DEFAULT NULL COMMENT '出库日期',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '客户类别id',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '开单人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开单人',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '发货审批状态',
  `shipments_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '发货状态(0未发货)',
  `audit_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '出库审批状态',
  `outbound_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '出库状态(0未出库)',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司地址',
  `warehouse_id` int(0) NULL DEFAULT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(0) NULL DEFAULT NULL,
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  PRIMARY KEY (`shipments_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售发货出库主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_shipments
-- ----------------------------
INSERT INTO `sys_shipments` VALUES (1, 'DN23182680012038144', 1, 'ON23182573573185536', '2020-11-18 00:00:00', '2020-11-18 00:00:00', 2, 'kh002', '客户002', 4, 1, NULL, '5', '1', '3', '1', NULL, NULL, NULL, NULL, NULL, 1, '1', '1', '超级管理员', '2020-11-18 12:04:31', '超级管理员', '2020-11-18 12:06:08', 'admin', '2020-11-18 12:06:08', '', 1, '2020-11-18 00:00:00');
INSERT INTO `sys_shipments` VALUES (2, 'DN23182712794718208', 1, 'ON23182573573185536', '2020-11-18 00:00:00', '2020-11-18 00:00:00', 2, 'kh002', '客户002', 4, 1, NULL, '5', '1', '3', '1', NULL, NULL, NULL, NULL, NULL, 1, '1', '1', '超级管理员', '2020-11-18 12:04:39', '超级管理员', '2020-11-18 12:05:58', 'admin', '2020-11-18 12:05:58', '', 1, '2020-11-18 00:00:00');
INSERT INTO `sys_shipments` VALUES (3, 'DN23182742314229760', 1, 'ON23182573573185536', '2020-11-18 00:00:00', '2020-11-18 00:00:00', 2, 'kh002', '客户002', 4, 1, NULL, '5', '1', '3', '1', NULL, NULL, NULL, NULL, NULL, 1, '1', '1', '超级管理员', '2020-11-18 12:04:46', '超级管理员', '2020-11-18 12:06:06', 'admin', '2020-11-18 12:06:06', '', 1, '2020-11-18 00:00:00');
INSERT INTO `sys_shipments` VALUES (4, 'DN23182780859883520', 1, 'ON23182573573185536', '2020-11-18 00:00:00', '2020-11-18 00:00:00', 2, 'kh002', '客户002', 4, 1, NULL, '5', '1', '3', '1', NULL, NULL, NULL, NULL, NULL, 1, '1', '1', '超级管理员', '2020-11-18 12:04:55', '超级管理员', '2020-11-18 12:05:55', 'admin', '2020-11-18 12:05:55', '', 1, '2020-11-18 00:00:00');
INSERT INTO `sys_shipments` VALUES (5, 'DN23183930975789056', 2, 'ON23183857403502592', '2020-11-18 00:00:00', '2020-11-18 00:00:00', 2, 'kh002', '客户002', 4, 1, NULL, '5', '1', '3', '1', NULL, NULL, NULL, NULL, NULL, 1, '1', '1', '超级管理员', '2020-11-18 12:09:29', '超级管理员', '2020-11-18 12:14:41', 'admin', '2020-11-18 12:14:41', '', 1, '2020-11-18 00:00:00');
INSERT INTO `sys_shipments` VALUES (6, 'DN23183951481741312', 2, 'ON23183857403502592', '2020-11-18 00:00:00', '2020-11-18 00:00:00', 2, 'kh002', '客户002', 4, 1, NULL, '5', '1', '3', '1', NULL, NULL, NULL, NULL, NULL, 1, '1', '1', '超级管理员', '2020-11-18 12:09:34', '超级管理员', '2020-11-18 12:14:37', 'admin', '2020-11-18 12:14:37', '', 1, '2020-11-18 00:00:00');
INSERT INTO `sys_shipments` VALUES (7, 'DN23228643975991296', 3, 'ON23228575579475968', '2020-11-18 15:07:08', NULL, 2, 'kh002', '客户002', 4, 1, NULL, '3', '1', '1', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 15:07:10', '超级管理员', '2020-11-18 15:28:30', 'admin', '2020-11-18 15:28:30', '', 1, '2020-11-18 00:00:00');
INSERT INTO `sys_shipments` VALUES (8, 'DN23528322546028544', 4, 'ON23528250865373184', '2020-11-19 10:57:55', NULL, 2, 'kh002', '客户002', 4, 1, NULL, '1', '0', '1', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-19 10:57:58', '超级管理员', '2020-11-19 10:57:58', NULL, NULL, '', 1, '2020-11-19 00:00:00');

-- ----------------------------
-- Table structure for sys_shipments_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_shipments_sub`;
CREATE TABLE `sys_shipments_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shipments_id` int(0) NOT NULL COMMENT '主表id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(0) NULL DEFAULT NULL COMMENT '订购数量',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `shipment_num` int(0) NULL DEFAULT NULL COMMENT '本次发货数量',
  `has_shipment_num` int(0) NULL DEFAULT NULL COMMENT '已发货数量',
  `outbound_num` int(0) NULL DEFAULT NULL COMMENT '本次出库数量',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `order_sub_id` bigint(0) NULL DEFAULT NULL COMMENT '订单子表id',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售发货出库子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_shipments_sub
-- ----------------------------
INSERT INTO `sys_shipments_sub` VALUES (1, 1, 2, 'beiz', '杯子', '耐高温', '小号', NULL, NULL, 2, '杯', 33.60, 2, NULL, 2, 0, 2, '超级管理员', '2020-11-18 12:04:31', '超级管理员', '2020-11-18 12:04:31', 5, 1);
INSERT INTO `sys_shipments_sub` VALUES (2, 2, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 2, NULL, 2, 0, 2, '超级管理员', '2020-11-18 12:04:39', '超级管理员', '2020-11-18 12:04:39', 4, 1);
INSERT INTO `sys_shipments_sub` VALUES (3, 3, 2, 'beiz', '杯子', '耐高温', '大号', NULL, NULL, 2, '杯', 33.60, 2, NULL, 2, 0, 2, '超级管理员', '2020-11-18 12:04:46', '超级管理员', '2020-11-18 12:04:46', 3, 1);
INSERT INTO `sys_shipments_sub` VALUES (4, 4, 4, 'xilianp', '洗脸盆', '内用、不易破', '中', NULL, NULL, 4, '个', 44.66, 1, NULL, 1, 0, 1, '超级管理员', '2020-11-18 12:04:55', '超级管理员', '2020-11-18 12:04:55', 2, 1);
INSERT INTO `sys_shipments_sub` VALUES (5, 5, 1, 'yg', '牙膏', '好用、便宜', '大人', NULL, NULL, 1, '支', 25.90, 32, NULL, 32, 0, 32, '超级管理员', '2020-11-18 12:09:29', '超级管理员', '2020-11-18 12:09:29', 6, 1);
INSERT INTO `sys_shipments_sub` VALUES (6, 6, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 43, NULL, 43, 0, 43, '超级管理员', '2020-11-18 12:09:34', '超级管理员', '2020-11-18 12:09:34', 7, 1);
INSERT INTO `sys_shipments_sub` VALUES (7, 7, 3, 'maojin', '毛巾', '洗脸、洗澡', '毛绒', NULL, NULL, 3, '条', 22.89, 3, NULL, 3, 0, 3, '超级管理员', '2020-11-18 15:07:10', '超级管理员', '2020-11-18 15:07:10', 8, 1);
INSERT INTO `sys_shipments_sub` VALUES (8, 8, 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, 4, '个', 44.66, 31, NULL, 31, 0, 31, '超级管理员', '2020-11-19 10:57:58', '超级管理员', '2020-11-19 10:57:58', 9, 1);

-- ----------------------------
-- Table structure for sys_signback
-- ----------------------------
DROP TABLE IF EXISTS `sys_signback`;
CREATE TABLE `sys_signback`  (
  `signback_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(0) NOT NULL COMMENT '订单id',
  `shipments_id` int(0) NOT NULL COMMENT '发货单id',
  `signback_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '签回单号',
  `shipments_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发货单号',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单单号',
  `sign_num` int(0) NULL DEFAULT 0 COMMENT '签收数',
  `rejection_num` int(0) NULL DEFAULT 0 COMMENT '拒收数',
  `signback_time` datetime(0) NULL DEFAULT NULL COMMENT '签回日期',
  `process_mode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理方式',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '签回人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '签回人',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司地址',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(0) NULL DEFAULT NULL,
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `signback_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '签收状态',
  `total_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '总计金额',
  PRIMARY KEY (`signback_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售签回主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_signback
-- ----------------------------
INSERT INTO `sys_signback` VALUES (1, 1, 4, 'SN23183033365372928', 'DN23182780859883520', 'ON23182573573185536', 1, 0, '2020-11-18 12:06:31', NULL, 2, 'kh002', '客户002', 1, '超级管理员', '3', NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 12:05:55', '超级管理员', '2020-11-18 12:06:47', NULL, NULL, '', 1, '2020-11-18 00:00:00', '1', 44.66);
INSERT INTO `sys_signback` VALUES (2, 1, 2, 'SN23183045604352000', 'DN23182712794718208', 'ON23182573573185536', 2, 0, '2020-11-18 12:06:25', NULL, 2, 'kh002', '客户002', 1, '超级管理员', '3', NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 12:05:58', '超级管理员', '2020-11-18 12:06:43', NULL, NULL, '', 1, '2020-11-18 00:00:00', '1', 89.32);
INSERT INTO `sys_signback` VALUES (3, 1, 3, 'SN23183077107769344', 'DN23182742314229760', 'ON23182573573185536', 2, 0, '2020-11-18 12:06:21', NULL, 2, 'kh002', '客户002', 1, '超级管理员', '3', NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 12:06:06', '超级管理员', '2020-11-18 12:06:39', NULL, NULL, '', 1, '2020-11-18 00:00:00', '1', 67.20);
INSERT INTO `sys_signback` VALUES (4, 1, 1, 'SN23183088520470528', 'DN23182680012038144', 'ON23182573573185536', 2, 0, '2020-11-18 12:06:18', NULL, 2, 'kh002', '客户002', 1, '超级管理员', '3', NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 12:06:08', '超级管理员', '2020-11-18 12:06:35', NULL, NULL, '', 1, '2020-11-18 00:00:00', '1', 67.20);
INSERT INTO `sys_signback` VALUES (5, 2, 6, 'SN23185223454760960', 'DN23183951481741312', 'ON23183857403502592', 43, 0, '2020-11-18 12:14:50', NULL, 2, 'kh002', '客户002', 1, '超级管理员', '3', NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 12:14:37', '超级管理员', '2020-11-18 12:14:59', NULL, NULL, '', 1, '2020-11-18 00:00:00', '1', 1920.38);
INSERT INTO `sys_signback` VALUES (6, 2, 5, 'SN23185238239682560', 'DN23183930975789056', 'ON23183857403502592', 32, 0, '2020-11-18 12:14:47', NULL, 2, 'kh002', '客户002', 1, '超级管理员', '3', NULL, NULL, NULL, NULL, NULL, '超级管理员', '2020-11-18 12:14:41', '超级管理员', '2020-11-18 12:14:56', NULL, NULL, '', 1, '2020-11-18 00:00:00', '1', 828.80);

-- ----------------------------
-- Table structure for sys_signback_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_signback_sub`;
CREATE TABLE `sys_signback_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `signback_id` int(0) NOT NULL COMMENT '主表id',
  `shipments_id` int(0) NOT NULL COMMENT '发货单主表id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '单价',
  `number` int(0) NULL DEFAULT 0 COMMENT '订购数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `sign_num` int(0) NULL DEFAULT 0 COMMENT '本次签收数量',
  `rejection_num` int(0) NULL DEFAULT NULL COMMENT '拒收数',
  `shipment_num` int(0) NULL DEFAULT 0 COMMENT '发货数量',
  `outbound_num` int(0) NULL DEFAULT 0 COMMENT '出库数量',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `order_sub_id` bigint(0) NULL DEFAULT NULL COMMENT '订单子表id',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售签回子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_signback_sub
-- ----------------------------
INSERT INTO `sys_signback_sub` VALUES (1, 1, 4, 4, 'xilianp', '洗脸盆', '内用、不易破', '中', NULL, NULL, 4, '个', 44.66, 1, 44.66, NULL, 1, NULL, 1, 1, '超级管理员', '2020-11-18 12:05:55', '超级管理员', '2020-11-18 12:06:32', 2, 1);
INSERT INTO `sys_signback_sub` VALUES (2, 2, 2, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 2, 89.32, NULL, 2, NULL, 2, 2, '超级管理员', '2020-11-18 12:05:58', '超级管理员', '2020-11-18 12:06:26', 4, 1);
INSERT INTO `sys_signback_sub` VALUES (3, 3, 3, 2, 'beiz', '杯子', '耐高温', '大号', NULL, NULL, 2, '杯', 33.60, 2, 67.20, NULL, 2, NULL, 2, 2, '超级管理员', '2020-11-18 12:06:06', '超级管理员', '2020-11-18 12:06:22', 3, 1);
INSERT INTO `sys_signback_sub` VALUES (4, 4, 1, 2, 'beiz', '杯子', '耐高温', '小号', NULL, NULL, 2, '杯', 33.60, 2, 67.20, NULL, 2, NULL, 2, 2, '超级管理员', '2020-11-18 12:06:08', '超级管理员', '2020-11-18 12:06:19', 5, 1);
INSERT INTO `sys_signback_sub` VALUES (5, 5, 6, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 43, 1920.38, NULL, 43, NULL, 43, 43, '超级管理员', '2020-11-18 12:14:37', '超级管理员', '2020-11-18 12:14:51', 7, 1);
INSERT INTO `sys_signback_sub` VALUES (6, 6, 5, 1, 'yg', '牙膏', '好用、便宜', '大人', NULL, NULL, 1, '支', 25.90, 32, 828.80, NULL, 32, NULL, 32, 32, '超级管理员', '2020-11-18 12:14:41', '超级管理员', '2020-11-18 12:14:48', 6, 1);

-- ----------------------------
-- Table structure for sys_storage
-- ----------------------------
DROP TABLE IF EXISTS `sys_storage`;
CREATE TABLE `sys_storage`  (
  `storage_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(0) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `source_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源类型',
  `source_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源单位',
  `storage_time` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '入库负责人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入库负责人',
  `category` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '类型',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `create_id` int(0) NULL DEFAULT NULL,
  `inout_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '0：产品入库，1：其他出库',
  `outbound_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出库类型',
  `outbound_time` datetime(0) NULL DEFAULT NULL COMMENT '出库日期',
  PRIMARY KEY (`storage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品入库主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_storage_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_storage_sub`;
CREATE TABLE `sys_storage_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `storage_id` int(0) NOT NULL COMMENT '入库主表id',
  `warehouse_id` int(0) NOT NULL COMMENT '仓库id',
  `materiel_id` int(0) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(0) NULL DEFAULT NULL COMMENT '入库数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品入库子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_supplier
-- ----------------------------
DROP TABLE IF EXISTS `sys_supplier`;
CREATE TABLE `sys_supplier`  (
  `supplier_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `supplier_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `supplier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司地址',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '业务员id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务员名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '启用状态（0正常 1停用）',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`supplier_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '供应商表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_supplier
-- ----------------------------
INSERT INTO `sys_supplier` VALUES (7, '1', '客户001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 1, 1, '超级管理员', '2020-12-04 11:18:56', NULL, NULL, NULL);
INSERT INTO `sys_supplier` VALUES (8, '2', '客户002', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 2, 1, '超级管理员', '2020-12-04 11:18:56', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_units
-- ----------------------------
DROP TABLE IF EXISTS `sys_units`;
CREATE TABLE `sys_units`  (
  `units_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '单位编号',
  `units_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位编码',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位名称',
  `units_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位类别（0基本单位，1包装单位）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`units_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '基本单位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_units
-- ----------------------------
INSERT INTO `sys_units` VALUES (1, 'zhi', '支', '0', '2020-11-16 11:59:35', '0', '0', '超级管理员', '2020-11-16 11:59:35', '超级管理员', NULL, 1);
INSERT INTO `sys_units` VALUES (2, 'bei', '杯', '0', '2020-11-16 11:59:51', '0', '0', '超级管理员', '2020-11-16 11:59:51', '超级管理员', NULL, 1);
INSERT INTO `sys_units` VALUES (3, 'tiao', '条', '0', '2020-11-16 12:01:37', '0', '0', '超级管理员', '2020-11-16 12:01:37', '超级管理员', NULL, 1);
INSERT INTO `sys_units` VALUES (4, 'ge', '个', '0', '2020-11-16 12:03:05', '0', '0', '超级管理员', '2020-11-16 12:03:05', '超级管理员', NULL, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录名称',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码',
  `user_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户编码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '加密盐',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别（0男 1女）',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'qq号码',
  `identity_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `now_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '现住址',
  `home_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像地址',
  `dept_id` int(0) NULL DEFAULT NULL COMMENT '部门id',
  `job_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '在职状态/0在职/1离职',
  `user_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '用户类型/0普通用户/1管理员',
  `start_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '启用状态/0启用/1禁用',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '逻辑删除状态（0代表存在 1代表删除）',
  `entry_time` datetime(0) NULL DEFAULT NULL COMMENT '入职时间',
  `resignation_time` datetime(0) NULL DEFAULT NULL COMMENT '离职时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 备注',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户档案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '4d7e8b29965cb324aa13909fecd0d948', 'admin', '超级管理员', '2de6ff7800bf08b8bd4c1e56c552c538', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', '1', '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (2, 'root', '92ae0b0cf1ce789d27ad7388341668b4', 'root', 'ROOT', '4e6b948d9f7c81f0d693d0d0dda27689', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', '1', '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (3, 'dev001', '8ba2adb4e6ded04c5f917a10ad363bb3', 'dev001', 'dev001', '543f58d9577831478ace59858e3bc20b', NULL, '', NULL, NULL, NULL, NULL, '1321', NULL, NULL, 2, '0', '1', '0', '0', '2020-11-03 00:00:00', NULL, '2020-11-16 12:25:16', '超级管理员', '2020-12-04 10:05:44', 'ROOT', NULL, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(0) NOT NULL COMMENT '用户ID',
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (3, 1);

-- ----------------------------
-- Table structure for sys_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `sys_warehouse`;
CREATE TABLE `sys_warehouse`  (
  `warehouse_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '仓库序号',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '仓库名称',
  `dept_id` int(0) NULL DEFAULT NULL COMMENT '部门id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '仓库状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_warehouse
-- ----------------------------
INSERT INTO `sys_warehouse` VALUES (1, '1', '1', NULL, '0', '0', '超级管理员', '2020-11-18 12:03:28', '超级管理员', '2020-11-18 12:03:28', NULL, 1);
INSERT INTO `sys_warehouse` VALUES (2, '2', '2', NULL, '0', '0', '超级管理员', '2020-12-03 14:54:30', NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_warehouse_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_warehouse_init`;
CREATE TABLE `sys_warehouse_init`  (
  `warehouse_id` int(0) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库初始化主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_warehouse_init
-- ----------------------------
INSERT INTO `sys_warehouse_init` VALUES (1, '1', '1', '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init` VALUES (2, '2', '2', '超级管理员', '2020-12-03 14:54:52', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_warehouse_init_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_warehouse_init_sub`;
CREATE TABLE `sys_warehouse_init_sub`  (
  `sub_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(0) NOT NULL COMMENT '仓库id',
  `materiel_id` int(0) NOT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(0) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(0) NULL DEFAULT NULL COMMENT '现存量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库初始化子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_warehouse_init_sub
-- ----------------------------
INSERT INTO `sys_warehouse_init_sub` VALUES (1, 1, 4, 'xilianp', '洗脸盆', '内用、不易破', '小', NULL, NULL, 4, '个', 44.66, 122, 5448.52, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (2, 1, 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, 4, '个', 44.66, 122, 5448.52, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (3, 1, 4, 'xilianp', '洗脸盆', '内用、不易破', '中', NULL, NULL, 4, '个', 44.66, 122, 5448.52, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (4, 1, 3, 'maojin', '毛巾', '洗脸、洗澡', '毛绒', NULL, NULL, 3, '条', 22.89, 122, 2792.58, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (5, 1, 3, 'maojin', '毛巾', '洗脸、洗澡', '丝绸', NULL, NULL, 3, '条', 22.89, 122, 2792.58, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (6, 1, 3, 'maojin', '毛巾', '洗脸、洗澡', '尼龙布', NULL, NULL, 3, '条', 22.89, 122, 2792.58, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (7, 1, 2, 'beiz', '杯子', '耐高温', '大号', NULL, NULL, 2, '杯', 33.60, 122, 4099.20, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (8, 1, 2, 'beiz', '杯子', '耐高温', '中号', NULL, NULL, 2, '杯', 33.60, 122, 4099.20, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (9, 1, 2, 'beiz', '杯子', '耐高温', '小号', NULL, NULL, 2, '杯', 33.60, 122, 4099.20, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (10, 1, 1, 'yg', '牙膏', '好用、便宜', '大人', NULL, NULL, 1, '支', 25.90, 122, 3159.80, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (11, 1, 1, 'yg', '牙膏', '好用、便宜', '小孩', NULL, NULL, 1, '支', 25.90, 122, 3159.80, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (12, 1, 1, 'yg', '牙膏', '好用、便宜', '老人', NULL, NULL, 1, '支', 25.90, 122, 3159.80, '超级管理员', '2020-11-18 12:03:56', '超级管理员', '2020-11-18 12:03:56', 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (13, 2, 4, 'xilianp', '洗脸盆', '内用、不易破', '大', NULL, NULL, 4, '个', 44.66, 321, 14335.86, '超级管理员', '2020-12-03 14:54:52', NULL, NULL, 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (14, 2, 6, 'yf', '衣服', '123434512512', '小', NULL, NULL, 3, '条', 123.00, 213, 26199.00, '超级管理员', '2020-12-03 14:54:52', NULL, NULL, 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (15, 2, 5, 'bzdsdd', '不知道啥东东', '不知道啥东东', '大', NULL, NULL, 4, '个', 99.99, 231, 23097.69, '超级管理员', '2020-12-03 14:54:52', NULL, NULL, 1);
INSERT INTO `sys_warehouse_init_sub` VALUES (16, 2, 3, 'maojin', '毛巾', '洗脸、洗澡', '毛绒', NULL, NULL, 3, '条', 22.89, 213, 4875.57, '超级管理员', '2020-12-03 14:54:52', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_writeoff
-- ----------------------------
DROP TABLE IF EXISTS `sys_writeoff`;
CREATE TABLE `sys_writeoff`  (
  `writeoff_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `writeoff_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '核销状态',
  `writeoff_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销金额',
  `writeoff_time` datetime(0) NULL DEFAULT NULL COMMENT '核销日期',
  `clientele_id` int(0) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建者id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `dept_id` int(0) NULL DEFAULT NULL COMMENT '部门id',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `dept_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门编码',
  `personnel_id` int(0) NULL DEFAULT NULL COMMENT '业务员id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务员名称',
  `personnel_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务员编码',
  PRIMARY KEY (`writeoff_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '应收核销表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_writeoff
-- ----------------------------
INSERT INTO `sys_writeoff` VALUES (1, '1', 1600.00, '2020-11-18 12:15:20', 2, 'kh002', '客户002', '0', 1, '超级管理员', '2020-11-18 12:15:27', '超级管理员', '2020-11-18 12:15:33', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_writeoff_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_writeoff_sub`;
CREATE TABLE `sys_writeoff_sub`  (
  `writeoff_sub_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `writeoff_id` int(0) NULL DEFAULT NULL COMMENT '子表id',
  `receipt_id` int(0) NULL DEFAULT NULL COMMENT '收款id',
  `receipt_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收款单号',
  `receipt_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '收款金额',
  `front_receipt_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销前收款金额',
  `back_receipt_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销后收款金后',
  `receivable_id` int(0) NULL DEFAULT NULL COMMENT '应收款id',
  `receivable_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应收款单号',
  `receivable_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '应收款金额',
  `front_receivable_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销前应收款金额',
  `back_receivable_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销后应收款金额',
  `writeoff_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '实际核销金额',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_id` int(0) NULL DEFAULT NULL COMMENT '创建者id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`writeoff_sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '应收核销明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_writeoff_sub
-- ----------------------------
INSERT INTO `sys_writeoff_sub` VALUES (1, 1, 1, 'CN23183510333235200', 500.00, 500.00, 432.80, 1, 'AR23183200445472768', 67.20, 67.20, 0.00, 67.20, '超级管理员', '2020-11-18 12:15:27', 1, '超级管理员', '2020-11-18 12:15:27');
INSERT INTO `sys_writeoff_sub` VALUES (2, 1, 1, 'CN23183510333235200', 500.00, 432.80, 365.60, 2, 'AR23183218808135680', 67.20, 67.20, 0.00, 67.20, '超级管理员', '2020-11-18 12:15:27', 1, '超级管理员', '2020-11-18 12:15:27');
INSERT INTO `sys_writeoff_sub` VALUES (3, 1, 1, 'CN23183510333235200', 500.00, 365.60, 276.28, 3, 'AR23183232938745856', 89.32, 89.32, 0.00, 89.32, '超级管理员', '2020-11-18 12:15:27', 1, '超级管理员', '2020-11-18 12:15:27');
INSERT INTO `sys_writeoff_sub` VALUES (4, 1, 1, 'CN23183510333235200', 500.00, 276.28, 231.62, 4, 'AR23183249292337152', 44.66, 44.66, 0.00, 44.66, '超级管理员', '2020-11-18 12:15:27', 1, '超级管理员', '2020-11-18 12:15:27');
INSERT INTO `sys_writeoff_sub` VALUES (5, 1, 1, 'CN23183510333235200', 500.00, 231.62, 0.00, 5, 'AR23185301238128640', 828.80, 828.80, 597.18, 231.62, '超级管理员', '2020-11-18 12:15:27', 1, '超级管理员', '2020-11-18 12:15:27');
INSERT INTO `sys_writeoff_sub` VALUES (6, 1, 2, 'CN23183540905517056', 100.00, 100.00, 0.00, 5, 'AR23185301238128640', 828.80, 597.18, 497.18, 100.00, '超级管理员', '2020-11-18 12:15:27', 1, '超级管理员', '2020-11-18 12:15:27');
INSERT INTO `sys_writeoff_sub` VALUES (7, 1, 3, 'CN23183570336948224', 1000.00, 1000.00, 502.82, 5, 'AR23185301238128640', 828.80, 497.18, 0.00, 497.18, '超级管理员', '2020-11-18 12:15:27', 1, '超级管理员', '2020-11-18 12:15:27');
INSERT INTO `sys_writeoff_sub` VALUES (8, 1, 3, 'CN23183570336948224', 1000.00, 502.82, 0.00, 6, 'AR23185316312457216', 1920.38, 1920.38, 1417.56, 502.82, '超级管理员', '2020-11-18 12:15:27', 1, '超级管理员', '2020-11-18 12:15:27');

SET FOREIGN_KEY_CHECKS = 1;
