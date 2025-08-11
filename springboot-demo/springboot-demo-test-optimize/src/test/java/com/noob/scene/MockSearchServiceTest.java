package com.noob.scene;


import com.noob.scene.impl.SearchServiceImpl;
import com.noob.scene.otherService.DBService;
import com.noob.scene.otherService.FileService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class MockSearchServiceTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private FileService fileService;

    @Mock
    private DBService dbService;

    @Before
    public void setUp() {
        // MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_search_success() {
        searchService.search("1", "hello world");
        verify(fileService).getFile();
        verify(dbService).update();
    }

    @Test
    public void test_search_success_skip() {
        searchService.search("skip", "hello world");
        verify(fileService, never()).getFile();
        verify(dbService, never()).update();
    }

    @Test
    public void test_search_success_mockOtherService() {
        // 定义 Mock 行为
        when(fileService.getFile()).thenReturn("mock_file_content");
        when(dbService.update()).thenReturn(1); // 假设返回 1 表示成功

        searchService.search("test", "hello world");
        verify(fileService).getFile();
        verify(dbService).update();


        verify(fileService, never()).getFile();
        verify(dbService, never()).update();
    }


}
