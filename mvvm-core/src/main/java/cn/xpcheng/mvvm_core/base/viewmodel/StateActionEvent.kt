package cn.xpcheng.mvvm_core.base.viewmodel

/**
 * @author chengxinping
 * @time 2020年05月18日11:53:11
 * @desc 密闭类 配合liveData+viewModel 统一loading、错误监听处理
 */
sealed class StateActionEvent

/**
 * loading状态
 */
object LoadingState : StateActionEvent()

/**
 * 成功状态 dismiss 进度条
 */
object SuccessState : StateActionEvent()


/**
 * 错误状态  toast or SnackBar
 */
class ErrorState(val message: String?) : StateActionEvent()