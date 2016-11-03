package com.weghst.konan;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import lombok.Data;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Data
@RefreshScope
@ConfigurationProperties("konan")
public class KonanProperties {


}
