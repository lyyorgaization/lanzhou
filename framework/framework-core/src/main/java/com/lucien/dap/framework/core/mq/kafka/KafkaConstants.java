package com.lucien.dap.framework.core.mq.kafka;

public class KafkaConstants {

    public static final String TOPIC_POINT_FILE_UPLOADED = "point_file";//埋点文件上传成功
    public static final String TOPIC_LOG_FILE_UPLOADED = "log_file";
    public static final String TOPIC_STREAM_FILTER = "stream_filter_";//+cubeId,经过过滤器后的流的topic_name
    public static final String TOPIC_STREAM_GROUP = "stream_group_";//+cubeId,经过分组后的流的topic_name

    public static final String TOPIC_POINT_DATA = "point_data_topic";

    public static final String REPORT_APPLICATION = "report_application";

    public static final String STREAM_APPLICATION_FILTER = "stream_application_filter_";//+cubeId
    public static final String STREAM_APPLICATION_GROUP = "stream_application_group_";//+cubeId
    public static final String STREAM_APPLICATION_MEASURE = "stream_application_measure_";//+measureId
}
