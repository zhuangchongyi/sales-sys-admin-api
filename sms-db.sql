/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : smsdb

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 14/11/2020 10:57:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_accessory
-- ----------------------------
DROP TABLE IF EXISTS `sys_accessory`;
CREATE TABLE `sys_accessory`  (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sub_id` bigint(20) NOT NULL COMMENT '目的产品编号',
  `materiel_id` bigint(20) NULL DEFAULT NULL COMMENT '来源产品编号',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储文件名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '展示文件名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片url',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传人',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_adjustment
-- ----------------------------
DROP TABLE IF EXISTS `sys_adjustment`;
CREATE TABLE `sys_adjustment`  (
  `adjustment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(11) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `adjustment_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '调整类型(0新增调整，1盘点调整)',
  `adjustment_time` datetime(0) NULL DEFAULT NULL COMMENT '调整时间',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调整负责人',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '调整人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`adjustment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '调整单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_adjustment_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_adjustment_sub`;
CREATE TABLE `sys_adjustment_sub`  (
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `adjustment_id` int(11) NOT NULL COMMENT '主表id',
  `warehouse_id` int(11) NULL DEFAULT NULL COMMENT '仓库id',
  `materiel_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `number` int(11) NULL DEFAULT NULL COMMENT '现存量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `adjust_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '调整金额',
  `adjust_num` int(11) NULL DEFAULT 0 COMMENT '调整数量',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '调整原因',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `adjust_total_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '调整前的总金额',
  `adjust_number` int(11) NULL DEFAULT 0 COMMENT '调整前的总数量',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '调整单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `category_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '类别编码',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '类别名称',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级类别id',
  `category` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型（0产品类别 1客户类别）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '自定义类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_clientele
-- ----------------------------
DROP TABLE IF EXISTS `sys_clientele`;
CREATE TABLE `sys_clientele`  (
  `clientele_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '客户名称',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '客户类别id',
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
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业务员id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '启用状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`clientele_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_clientele_product
-- ----------------------------
DROP TABLE IF EXISTS `sys_clientele_product`;
CREATE TABLE `sys_clientele_product`  (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clientele_id` int(11) NOT NULL COMMENT '客户id',
  `materiel_id` int(11) NOT NULL COMMENT '产品编号',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
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
  `category_id` int(11) NULL DEFAULT NULL COMMENT '产品类别id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品类别',
  `pk_id` bigint(11) NULL DEFAULT NULL COMMENT '产品型号id',
  `create_id` int(11) NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_code_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_rule`;
CREATE TABLE `sys_code_rule`  (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
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
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门编码',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '祖级列表',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父部门id',
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
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门档案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_finance_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_finance_init`;
CREATE TABLE `sys_finance_init`  (
  `init_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clientele_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `receive_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '应收金额',
  `finance_time` datetime(0) NULL DEFAULT NULL COMMENT '财务日期',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
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
  `inventory_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(11) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `inventory_time` datetime(0) NULL DEFAULT NULL COMMENT '盘点时间',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '盘点负责人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盘点负责人',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '审核者',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `adjustment_id` int(11) NULL DEFAULT NULL COMMENT '调整单id',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`inventory_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '盘点单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_inventory_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_inventory_sub`;
CREATE TABLE `sys_inventory_sub`  (
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `inventory_id` int(11) NOT NULL COMMENT '初始化主表id',
  `warehouse_id` int(11) NULL DEFAULT NULL COMMENT '仓库id',
  `materiel_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '现存总金额',
  `number` int(11) NULL DEFAULT NULL COMMENT '现存数量',
  `reality_num` int(11) NULL DEFAULT 0 COMMENT '实盘数',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `adjustment_id` int(11) NULL DEFAULT NULL COMMENT '调整单id',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '盘点单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_materiel
-- ----------------------------
DROP TABLE IF EXISTS `sys_materiel`;
CREATE TABLE `sys_materiel`  (
  `materiel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品编号',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '产品类型id',
  `category_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品类型编码',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品类型名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
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
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`materiel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品档案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_materiel_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_materiel_file`;
CREATE TABLE `sys_materiel_file`  (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `materiel_id` int(11) NOT NULL COMMENT '产品编号',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存储文件名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '展示文件名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片url',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传人',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_materiel_model
-- ----------------------------
DROP TABLE IF EXISTS `sys_materiel_model`;
CREATE TABLE `sys_materiel_model`  (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `m_id` int(11) NOT NULL COMMENT '产品id',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '型号',
  PRIMARY KEY (`pk_id`, `m_id`, `model_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品型号关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父菜单ID',
  `order_num` tinyint(4) NULL DEFAULT 1 COMMENT '显示顺序',
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
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60103 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (10000, '系统管理', 0, 1, 'system', '', '0', '0', 'M', '0', NULL, 'el-icon-setting', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
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
INSERT INTO `sys_menu` VALUES (20000, '基础档案', 0, 2, 'basis', '', '0', '0', 'M', '0', NULL, 'el-icon-s-help', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
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
INSERT INTO `sys_menu` VALUES (30000, '销售管理', 0, 3, 'sales', NULL, '0', '0', 'M', '0', NULL, 'el-icon-s-order', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
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
INSERT INTO `sys_menu` VALUES (40000, '仓库管理', 0, 4, 'warehouse', NULL, '0', '0', 'M', '0', NULL, 'el-icon-coin', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
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
INSERT INTO `sys_menu` VALUES (50000, '财务管理', 0, 5, 'finance', NULL, '0', '0', 'M', '0', NULL, 'el-icon-s-data', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
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
INSERT INTO `sys_menu` VALUES (60000, '系统监控', 0, 6, 'monitor', '', '0', '0', 'M', '0', NULL, 'el-icon-discover', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (60100, '在线用户', 60000, 1, '/monitor/online', '/monitor/online/index', '0', '0', 'C', '0', NULL, 'el-icon-star-on', 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (60101, '列表', 60100, 1, NULL, NULL, '0', '0', 'F', '0', 'monitor:online:list', NULL, 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);
INSERT INTO `sys_menu` VALUES (60102, '下线', 60100, 2, NULL, NULL, '0', '0', 'F', '0', 'monitor:online:kickout', NULL, 'admin', '2020-08-31 00:00:00', NULL, NULL, '', NULL);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
  `delivery_time` datetime(0) NULL DEFAULT NULL COMMENT '交货日期',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `clientele_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '客户类别id',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '开单人id',
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
  `create_id` int(11) NULL DEFAULT NULL,
  `receipt_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计收款',
  `signback_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计签收金额（累计应收款金额）',
  `shipment_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计发货金额',
  `outbound_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '累计出库金额',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售订单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_order_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_order_sub`;
CREATE TABLE `sys_order_sub`  (
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(11) NOT NULL COMMENT '主表id',
  `materiel_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `has_shipment_num` int(11) NULL DEFAULT 0 COMMENT '已发货数',
  `has_outbound_num` int(11) NULL DEFAULT 0 COMMENT '已出库数',
  `has_signback_num` int(11) NULL DEFAULT 0 COMMENT '已签收数',
  `has_return_num` int(11) NULL DEFAULT 0 COMMENT '退货数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售订单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_quotation
-- ----------------------------
DROP TABLE IF EXISTS `sys_quotation`;
CREATE TABLE `sys_quotation`  (
  `quotation_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quotation_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报价单号',
  `quotation_time` datetime(0) NULL DEFAULT NULL COMMENT '报价日期',
  `clientele_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '客户类别id',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '报价人id',
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
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`quotation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售报价主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_quotation_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_quotation_sub`;
CREATE TABLE `sys_quotation_sub`  (
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `quotation_id` int(11) NOT NULL COMMENT '主表id',
  `materiel_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '产品类别id',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售报价子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_receipt
-- ----------------------------
DROP TABLE IF EXISTS `sys_receipt`;
CREATE TABLE `sys_receipt`  (
  `receipt_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receipt_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收款单号',
  `receipt_time` datetime(0) NULL DEFAULT NULL COMMENT '收款日期',
  `receipt_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '收款金额',
  `receipt_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收款账号',
  `payment_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收款方式',
  `payment_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '付款账号',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `clientele_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '业务员id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务员id',
  `personnel_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务员id',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '业务部门id',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务部门名称',
  `dept_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务部门编码',
  `has_verifica_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '已核销金额',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`receipt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '财务收款表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_receivable
-- ----------------------------
DROP TABLE IF EXISTS `sys_receivable`;
CREATE TABLE `sys_receivable`  (
  `receivable_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receivable_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应收单号',
  `clientele_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
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
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `source_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '来源类型',
  `has_verifica_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '已核销金额',
  PRIMARY KEY (`receivable_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '财务应收款主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_repertory
-- ----------------------------
DROP TABLE IF EXISTS `sys_repertory`;
CREATE TABLE `sys_repertory`  (
  `pk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(11) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `materiel_id` int(11) NOT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(11) NULL DEFAULT NULL COMMENT '现存量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品现存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_returns
-- ----------------------------
DROP TABLE IF EXISTS `sys_returns`;
CREATE TABLE `sys_returns`  (
  `returns_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `returns_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '退货单号',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单单号',
  `returns_time` datetime(0) NULL DEFAULT NULL COMMENT '退货日期',
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `clientele_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '客户类别id',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '开单人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开单人名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司地址',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '退货总金额',
  `warehouse_id` int(11) NULL DEFAULT NULL COMMENT '仓库id',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `audit_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '入库审批状态',
  `storage_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '入库状态',
  `storage_id` int(11) NULL DEFAULT NULL COMMENT '入库人id',
  `storage_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入库人',
  `storage_time` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`returns_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售退货主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_returns_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_returns_sub`;
CREATE TABLE `sys_returns_sub`  (
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `returns_id` int(11) NOT NULL COMMENT '主表id',
  `materiel_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(11) NULL DEFAULT NULL COMMENT '订购数量',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `has_shipment_num` int(11) NULL DEFAULT 0 COMMENT '已发货数量',
  `has_outbound_num` int(11) NULL DEFAULT 0 COMMENT '已出库数量',
  `has_signback_num` int(11) NULL DEFAULT 0 COMMENT '已签收数量',
  `has_returns_num` int(11) NULL DEFAULT 0 COMMENT '已退货数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '退货金额',
  `reality_num` int(11) NULL DEFAULT 0 COMMENT '实际入库数量',
  `returns_num` int(11) NULL DEFAULT 0 COMMENT '本次退货数量',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `order_sub_id` bigint(20) NULL DEFAULT NULL COMMENT '订单子表id',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售退货子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_num` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编码',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `dept_id` int(11) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_scrap
-- ----------------------------
DROP TABLE IF EXISTS `sys_scrap`;
CREATE TABLE `sys_scrap`  (
  `scrap_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(11) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `total_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '报废总金额',
  `scrap_time` datetime(0) NULL DEFAULT NULL COMMENT '报废时间',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报废负责人',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '报废人id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`scrap_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报废单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_scrap_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_scrap_sub`;
CREATE TABLE `sys_scrap_sub`  (
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scrap_id` int(11) NOT NULL COMMENT '主表id',
  `warehouse_id` int(11) NULL DEFAULT NULL COMMENT '仓库id',
  `materiel_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `scrap_num` int(11) NULL DEFAULT 0 COMMENT '报废数',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报废原因',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `number` int(11) NULL DEFAULT NULL COMMENT '现存数量',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报废单子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_shipments
-- ----------------------------
DROP TABLE IF EXISTS `sys_shipments`;
CREATE TABLE `sys_shipments`  (
  `shipments_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shipments_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发货单号',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单单号',
  `shipments_time` datetime(0) NULL DEFAULT NULL COMMENT '发货日期',
  `outbound_time` datetime(0) NULL DEFAULT NULL COMMENT '出库日期',
  `clientele_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '客户类别id',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '开单人id',
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
  `warehouse_id` int(11) NULL DEFAULT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(11) NULL DEFAULT NULL,
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  PRIMARY KEY (`shipments_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售发货出库主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_shipments_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_shipments_sub`;
CREATE TABLE `sys_shipments_sub`  (
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shipments_id` int(11) NOT NULL COMMENT '主表id',
  `materiel_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(11) NULL DEFAULT NULL COMMENT '订购数量',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `shipment_num` int(11) NULL DEFAULT NULL COMMENT '本次发货数量',
  `has_shipment_num` int(11) NULL DEFAULT NULL COMMENT '已发货数量',
  `outbound_num` int(11) NULL DEFAULT NULL COMMENT '本次出库数量',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `order_sub_id` bigint(20) NULL DEFAULT NULL COMMENT '订单子表id',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售发货出库子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_signback
-- ----------------------------
DROP TABLE IF EXISTS `sys_signback`;
CREATE TABLE `sys_signback`  (
  `signback_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `shipments_id` int(11) NOT NULL COMMENT '发货单id',
  `signback_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '签回单号',
  `shipments_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发货单号',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单单号',
  `sign_num` int(11) NULL DEFAULT 0 COMMENT '签收数',
  `rejection_num` int(11) NULL DEFAULT 0 COMMENT '拒收数',
  `signback_time` datetime(0) NULL DEFAULT NULL COMMENT '签回日期',
  `process_mode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理方式',
  `clientele_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '签回人id',
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
  `create_id` int(11) NULL DEFAULT NULL,
  `order_time` datetime(0) NULL DEFAULT NULL COMMENT '订单日期',
  `signback_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '签收状态',
  `total_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '总计金额',
  PRIMARY KEY (`signback_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售签回主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_signback_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_signback_sub`;
CREATE TABLE `sys_signback_sub`  (
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `signback_id` int(11) NOT NULL COMMENT '主表id',
  `shipments_id` int(11) NOT NULL COMMENT '发货单主表id',
  `materiel_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '单价',
  `number` int(11) NULL DEFAULT 0 COMMENT '订购数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '技术要求',
  `sign_num` int(11) NULL DEFAULT 0 COMMENT '本次签收数量',
  `rejection_num` int(11) NULL DEFAULT NULL COMMENT '拒收数',
  `shipment_num` int(11) NULL DEFAULT 0 COMMENT '发货数量',
  `outbound_num` int(11) NULL DEFAULT 0 COMMENT '出库数量',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `order_sub_id` bigint(20) NULL DEFAULT NULL COMMENT '订单子表id',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '销售签回子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_storage
-- ----------------------------
DROP TABLE IF EXISTS `sys_storage`;
CREATE TABLE `sys_storage`  (
  `storage_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(11) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `source_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源类型',
  `source_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源单位',
  `storage_time` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '入库负责人id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '入库负责人',
  `category` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '类型',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `create_id` int(11) NULL DEFAULT NULL,
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
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `storage_id` int(11) NOT NULL COMMENT '入库主表id',
  `warehouse_id` int(11) NOT NULL COMMENT '仓库id',
  `materiel_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(11) NULL DEFAULT NULL COMMENT '入库数量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品入库子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_units
-- ----------------------------
DROP TABLE IF EXISTS `sys_units`;
CREATE TABLE `sys_units`  (
  `units_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单位编号',
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
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`units_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '基本单位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
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
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
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
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户档案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '4d7e8b29965cb324aa13909fecd0d948', 'admin', '超级管理员', '2de6ff7800bf08b8bd4c1e56c552c538', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', '1', '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (2, 'root', '92ae0b0cf1ce789d27ad7388341668b4', 'root', 'ROOT', '4e6b948d9f7c81f0d693d0d0dda27689', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', '1', '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `sys_warehouse`;
CREATE TABLE `sys_warehouse`  (
  `warehouse_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '仓库序号',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '仓库名称',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '仓库状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_warehouse_init
-- ----------------------------
DROP TABLE IF EXISTS `sys_warehouse_init`;
CREATE TABLE `sys_warehouse_init`  (
  `warehouse_id` int(11) NOT NULL COMMENT '仓库id',
  `warehouse_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库编码',
  `warehouse_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库初始化主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_warehouse_init_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_warehouse_init_sub`;
CREATE TABLE `sys_warehouse_init_sub`  (
  `sub_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `warehouse_id` int(11) NOT NULL COMMENT '仓库id',
  `materiel_id` int(11) NOT NULL COMMENT '产品id',
  `materiel_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品编码',
  `materiel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规格',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `need_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所需扭矩',
  `out_torque` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输出扭矩',
  `units_id` int(11) NULL DEFAULT NULL COMMENT '基本单位id',
  `units_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` int(11) NULL DEFAULT NULL COMMENT '现存量',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库初始化子表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_writeoff
-- ----------------------------
DROP TABLE IF EXISTS `sys_writeoff`;
CREATE TABLE `sys_writeoff`  (
  `writeoff_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `writeoff_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '核销状态',
  `writeoff_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销金额',
  `writeoff_time` datetime(0) NULL DEFAULT NULL COMMENT '核销日期',
  `clientele_id` int(11) NULL DEFAULT NULL COMMENT '客户id',
  `clientele_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编码',
  `clientele_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名称',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建者id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `audit_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `dept_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门编码',
  `personnel_id` int(11) NULL DEFAULT NULL COMMENT '业务员id',
  `personnel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务员名称',
  `personnel_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务员编码',
  PRIMARY KEY (`writeoff_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '应收核销表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_writeoff_sub
-- ----------------------------
DROP TABLE IF EXISTS `sys_writeoff_sub`;
CREATE TABLE `sys_writeoff_sub`  (
  `writeoff_sub_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `writeoff_id` int(11) NULL DEFAULT NULL COMMENT '子表id',
  `receipt_id` int(11) NULL DEFAULT NULL COMMENT '收款id',
  `receipt_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收款单号',
  `receipt_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '收款金额',
  `front_receipt_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销前收款金额',
  `back_receipt_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销后收款金后',
  `receivable_id` int(11) NULL DEFAULT NULL COMMENT '应收款id',
  `receivable_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应收款单号',
  `receivable_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '应收款金额',
  `front_receivable_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销前应收款金额',
  `back_receivable_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '核销后应收款金额',
  `writeoff_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '实际核销金额',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_id` int(11) NULL DEFAULT NULL COMMENT '创建者id',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`writeoff_sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '应收核销明细表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
