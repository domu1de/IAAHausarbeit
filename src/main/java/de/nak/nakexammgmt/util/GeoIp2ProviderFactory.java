/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.GeoIp2Provider;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Factory-Bean to create an instance of {@link GeoIp2Provider}.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class GeoIp2ProviderFactory {

    private Resource resource;

    public GeoIp2Provider create() throws IOException {
        return new DatabaseReader.Builder(resource.getFile()).build();
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
