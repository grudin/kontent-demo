package com.trustedchoice.kontentdemo;

import kentico.kontent.delivery.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SitemapLinkResolver implements ContentLinkUrlResolver {

    private final static Logger LOG = LoggerFactory.getLogger(SitemapLinkResolver.class);

    private final DeliveryClient deliveryClient;

    SitemapLinkResolver(DeliveryClient deliveryClient) {
        this.deliveryClient = deliveryClient;
    }

    @Override
    public String resolveLinkUrl(Link link) {
        final String codename = link.getCodename();

        LOG.debug("Resolving link for codename: {}", codename);

        final List<NameValuePair> params = DeliveryParameterBuilder.params()
                .projection("url")
                .build();

        final ContentItem item = deliveryClient.getItem(codename, params).getItem();
        final String urlSlug = ((UrlSlugElement) item.getElements().get("url")).getValue();

        LOG.debug("Resolved link: {} for codename: {}", urlSlug, codename);

        return urlSlug;
    }
}
