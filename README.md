###[账号]
####注册（密码至少6位，数字/小写字母/大写字母/特殊字符至少2种）
####重置密码
####登录，登录要返回账号相关信息
###[标签]
####添加
####删除
###[群组]
####添加
####添加子群组
####添加账号
####通过树、缓存实现
###[好友]
####好友添加(判断重复)
####修改备注名
###[查询]
####查询一个人的好友（分页，根据等级降序，有备注名的显示备注名）
###[缓存]
####Spring启动时把整个群组缓存起来(实现InitializingBean)
####添加和删除群组，都要去同步这个缓存
