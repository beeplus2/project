export default {
    // 手机号码验证
    telephone: (rule, value, callback) => {
        if (value === '') {
            callback()
            return
        }
        const pass = /^(\\+86)?0?[1][34578][0-9]{9}$/g.test(value)
        if (!pass) {
            callback(new Error(rule.message || '请输入正确的电话号码'))
        }
        callback()
    },
    // 邮箱验证
    email: (rule, value, callback) => {
        if (value === '') {
            callback()
            return
        }
        const pass = /^[a-zA-Z0-9_.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/g.test(
            value
        )
        if (!pass) {
            callback(new Error(rule.message || '请输入正确的邮箱'))
        }
        callback()
    },
    // 只能输入汉字
    chinese: (rule, value, callback) => {
        if (value === '') {
            callback()
            return
        }
        const pass = /^[\u4e00-\u9fa5]*$/g.test(
            value
        )
        if (!pass) {
            callback(new Error(rule.message || '只能输入汉字'))
        }
        callback()
    },
}