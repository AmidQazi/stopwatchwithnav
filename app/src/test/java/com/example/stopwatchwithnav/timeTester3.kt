package com.example.stopwatchwithnav

class timeTester3 {
    @Test
    fun `test formatTime`() {
        val fragment = NotificationsFragment()
        val formattedTime = fragment.formatTime(3661)
        assertEquals("01:01:01", formattedTime)
    }
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: NotificationsViewModel

    @Before
    fun setup() {
        viewModel = NotificationsViewModel()
    }

    @Test
    fun testTimerFunctionality() {
        viewModel.startTimer()

        Thread.sleep(100)

        assertEquals(1, viewModel.timerValue.value?.toInt())

        viewModel.pauseTimer()

        Thread.sleep(1000)

        assertEquals(1, viewModel.timerValue.value?.toInt())

        viewModel.resetTimer()

        assertEquals(0, viewModel.timerValue.value?.toInt())
    }

}