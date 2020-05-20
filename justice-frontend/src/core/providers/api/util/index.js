export const getUrlByQueryPaginate = (url, query) => {
    url += 'size=' + query.pageSize ;
    url += '&page=' + query.page;
    if (query.orderBy) {
      url += '&sort=' + query.orderBy.field;
      url += ',' + query.orderDirection;
    }
    return url;
}