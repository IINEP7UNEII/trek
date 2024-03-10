/**
 * Minified by jsDelivr using Terser v5.19.2.
 * Original file: /npm/ngeohash@0.6.3/main.js
 *
 * Do NOT use SRI with dynamically generated files! More information: https://www.jsdelivr.com/using-sri-with-dynamic-files
 */
for(var BASE32_CODES="0123456789bcdefghjkmnpqrstuvwxyz",BASE32_CODES_DICT={},i=0;i<BASE32_CODES.length;i++)BASE32_CODES_DICT[BASE32_CODES.charAt(i)]=i;var ENCODE_AUTO="auto",MIN_LAT=-90,MAX_LAT=90,MIN_LON=-180,MAX_LON=180,SIGFIG_HASH_LENGTH=[0,5,7,8,11,12,13,15,16,17,18],encode=function(e,n,o){if(o===ENCODE_AUTO){if("number"==typeof e||"number"==typeof n)throw new Error("string notation required for auto precision.");var r=e.split(".")[1].length,t=n.split(".")[1].length,i=Math.max(r,t);o=SIGFIG_HASH_LENGTH[i]}else void 0===o&&(o=9);for(var _,d=[],u=0,a=0,c=0,b=MAX_LAT,l=MIN_LAT,A=MAX_LON,g=MIN_LON;d.length<o;)if(a%2==0?n>(_=(A+g)/2)?(c=1+(c<<1),g=_):(c=0+(c<<1),A=_):e>(_=(b+l)/2)?(c=1+(c<<1),l=_):(c=0+(c<<1),b=_),a++,5===++u){var h=BASE32_CODES[c];d.push(h),u=0,c=0}return d.join("")},encode_int=function(e,n,o){o=o||52;for(var r,t=0,i=MAX_LAT,_=MIN_LAT,d=MAX_LON,u=MIN_LON,a=0;t<o;)a*=2,t%2==0?n>(r=(d+u)/2)?(a+=1,u=r):d=r:e>(r=(i+_)/2)?(a+=1,_=r):i=r,t++;return a},decode_bbox=function(e){for(var n,o=!0,r=MAX_LAT,t=MIN_LAT,i=MAX_LON,_=MIN_LON,d=0,u=0,a=e.length;u<a;u++){var c=e[u].toLowerCase();d=BASE32_CODES_DICT[c];for(var b=4;b>=0;b--){var l=d>>b&1;o?(n=(i+_)/2,1===l?_=n:i=n):(n=(r+t)/2,1===l?t=n:r=n),o=!o}}return[t,_,r,i]},decode_bbox_int=function(e,n){for(var o=MAX_LAT,r=MIN_LAT,t=MAX_LON,i=MIN_LON,_=0,d=(n=n||52)/2,u=0;u<d;u++)_=get_bit(e,2*(d-u)-1),0===get_bit(e,2*(d-u)-2)?o=(o+r)/2:r=(o+r)/2,0===_?t=(t+i)/2:i=(t+i)/2;return[r,i,o,t]};function get_bit(e,n){return e/Math.pow(2,n)&1}var decode=function(e){var n=decode_bbox(e),o=(n[0]+n[2])/2,r=(n[1]+n[3])/2;return{latitude:o,longitude:r,error:{latitude:n[2]-o,longitude:n[3]-r}}},decode_int=function(e,n){var o=decode_bbox_int(e,n),r=(o[0]+o[2])/2,t=(o[1]+o[3])/2;return{latitude:r,longitude:t,error:{latitude:o[2]-r,longitude:o[3]-t}}},neighbor=function(e,n){var o=decode(e),r=o.latitude+n[0]*o.error.latitude*2,t=o.longitude+n[1]*o.error.longitude*2;return t=ensure_valid_lon(t),r=ensure_valid_lat(r),encode(r,t,e.length)},neighbor_int=function(e,n,o){var r=decode_int(e,o=o||52),t=r.latitude+n[0]*r.error.latitude*2,i=r.longitude+n[1]*r.error.longitude*2;return i=ensure_valid_lon(i),t=ensure_valid_lat(t),encode_int(t,i,o)},neighbors=function(e){var n,o,r=e.length,t=decode(e),i=t.latitude,_=t.longitude,d=2*t.error.latitude,u=2*t.error.longitude;function a(e,t){return n=i+e*d,o=ensure_valid_lon(o=_+t*u),n=ensure_valid_lat(n),encode(n,o,r)}return[a(1,0),a(1,1),a(0,1),a(-1,1),a(-1,0),a(-1,-1),a(0,-1),a(1,-1)]},neighbors_int=function(e,n){var o,r,t=decode_int(e,n=n||52),i=t.latitude,_=t.longitude,d=2*t.error.latitude,u=2*t.error.longitude;function a(e,t){return o=i+e*d,r=ensure_valid_lon(r=_+t*u),o=ensure_valid_lat(o),encode_int(o,r,n)}return[a(1,0),a(1,1),a(0,1),a(-1,1),a(-1,0),a(-1,-1),a(0,-1),a(1,-1)]},bboxes=function(e,n,o,r,t){for(var i=encode(e,n,t=t||9),_=encode(o,r,t),d=decode(i),u=2*d.error.latitude,a=2*d.error.longitude,c=decode_bbox(i),b=decode_bbox(_),l=Math.round((b[0]-c[0])/u),A=Math.round((b[1]-c[1])/a),g=[],h=0;h<=l;h++)for(var M=0;M<=A;M++)g.push(neighbor(i,[h,M]));return g},bboxes_int=function(e,n,o,r,t){for(var i=encode_int(e,n,t=t||52),_=encode_int(o,r,t),d=decode_int(i,t),u=2*d.error.latitude,a=2*d.error.longitude,c=decode_bbox_int(i,t),b=decode_bbox_int(_,t),l=Math.round((b[0]-c[0])/u),A=Math.round((b[1]-c[1])/a),g=[],h=0;h<=l;h++)for(var M=0;M<=A;M++)g.push(neighbor_int(i,[h,M],t));return g};function ensure_valid_lon(e){return e>MAX_LON?MIN_LON+e%MAX_LON:e<MIN_LON?MAX_LON+e%MAX_LON:e}function ensure_valid_lat(e){return e>MAX_LAT?MAX_LAT:e<MIN_LAT?MIN_LAT:e}var geohash={ENCODE_AUTO:ENCODE_AUTO,encode:encode,encode_uint64:encode_int,encode_int:encode_int,decode:decode,decode_int:decode_int,decode_uint64:decode_int,decode_bbox:decode_bbox,decode_bbox_uint64:decode_bbox_int,decode_bbox_int:decode_bbox_int,neighbor:neighbor,neighbor_int:neighbor_int,neighbors:neighbors,neighbors_int:neighbors_int,bboxes:bboxes,bboxes_int:bboxes_int};window.Geohash = geohash;
//# sourceMappingURL=/sm/7c1a10d468fde9000a9176f81d522739359a2f25e528cfc73563b6022ed7e2f5.map