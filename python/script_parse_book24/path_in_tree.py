BOOK_LINKS_PATH = "//div[@class='product-list__item']/article/div/a/@href"

CHARACTERISTIC_VALUES_PATH = "//ul[@class='product-characteristic__list']/li[" \
                             "@class='product-characteristic__item-holder']/div[" \
                             "@class='product-characteristic__item']/div[" \
                             "@class='product-characteristic__value']//text() "

CHARACTERISTIC_LABELS_PATH = "//ul[@class='product-characteristic__list']/li[" \
                             "@class='product-characteristic__item-holder']/div[" \
                             "@class='product-characteristic__item']/div[" \
                             "@class='product-characteristic__label-holder']/span[" \
                             "@class='product-characteristic__label']//text() "

TITLE_PATH = "//div[@class='breadcrumbs product-detail-page__breadcrumbs']/ol/li/span/text()"

ANNOTATION_PATH = "//div[@class='product-about product-detail-page__product-about']/div[" \
                  "@class='product-about__additional']/div[@class='product-about__text']//text() "

PRODUCT_ID_PATH = "//div[@class='product-detail-page__main-holder']/div[" \
                  "@class='product-detail-page__title-holder']/p[@class='product-detail-page__article']/text() "

IMAGE_PATH = "//div[@class='product-poster__main-slide']//img/@src"