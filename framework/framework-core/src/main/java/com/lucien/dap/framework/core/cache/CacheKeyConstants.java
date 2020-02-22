package com.lucien.dap.framework.core.cache;

public class CacheKeyConstants {
    public final static String LIMIT_IMEI_PREFIX = "limit_imei_";
    public final static String LIMIT_APP_PREFIX = "limit_appKey_";
    public final static String LIMIT_API_PREFIX = "limit_api_";

    public final static String CUBE_ENTITY = "cube_entity_";//拼接id
    public final static String CUBE_LIST_BY_TAG = "cubelist_tag_";//拼接tagcode
    public final static String DIMENSION_LIST_BY_CUBE = "dimlist_cube_";//拼接cubeid
    public final static String DIMENSION_NAME_ID = "dim_name_id_";//拼接字段名
    public final static String DIMENSION_ENTITY = "dim_entity_";//拼接ID
    public final static String DIMENSION_DICTIONARY_VALUE_ID = "dim_dic_value_id_";//拼接维度ID,缓存值是一个map,字典值和ID的键值对
    public final static String DIMENSION_DICTIONARY_ENTITY = "dim_dic_";//拼接字典ID
    public final static String DIMENSION_DICTIONARY_LIST_BY_DIMENSION = "dim_dic_dim_";//拼接维度ID,缓存value是字典id集合
    public final static String MEASURE_LIST_BY_CUBE = "measurelist_cube_";//拼接cubeId

    public final static String TERMINAL_ACTIVE_LOCK = "term_active_lock_";//激活锁定
    public final static int TERMINAL_ACTIVE_LOCK_EXPIRE = 3;
    public final static String MEASURE_ENTITY = "measure_entity_";//拼接ID

    public final static String OMP_LOGIN_TOKEN = "omp_login_token_";
    public final static int OMP_LOGIN_EXPIRE = 30 * 60;

    public final static String LOG_PROJECT_ADD_LOCK = "log_project_add_lock_";//日志上传项目号新增锁
    public final static String LOG_PROJECT_CODE_ID = "log_project_ci_";//日志上传项目号对应的ID

    public final static String LOG_VERSION_ADD_LOCK = "log_version_add_lock_";
    public final static String LOG_VERSION_CODE_ID = "log_verion_ci_";
}