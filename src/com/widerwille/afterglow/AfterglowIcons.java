package com.widerwille.afterglow;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.util.PlatformUtils;
import com.sun.istack.internal.Nullable;

import javax.swing.*;

public class AfterglowIcons
{
	public static final Icon DIRECTORY = IconLoader.getIcon("/icons/folder.png");
	public static final Icon ANY = IconLoader.getIcon("/icons/file_type_default.png");
	public static final Icon CFILE = IconLoader.getIcon("/icons/file_type_c.png");
	public static final Icon CPPFILE = IconLoader.getIcon("/icons/file_type_c++.png");
	public static final Icon MFILE = IconLoader.getIcon("/icons/file_type_objectivec.png");
	public static final Icon HEADER = IconLoader.getIcon("/icons/file_type_header.png");
	public static final Icon RUBY = IconLoader.getIcon("/icons/file_type_ruby.png");
	public static final Icon PYTHON = IconLoader.getIcon("/icons/file_type_python.png");
	public static final Icon JAVASCRIPT = IconLoader.getIcon("/icons/file_type_js.png");
	public static final Icon SHELL = IconLoader.getIcon("/icons/file_type_source.png");
	public static final Icon MARKDOWN = IconLoader.getIcon("/icons/file_type_markdown.png");
	public static final Icon GIT = IconLoader.getIcon("/icons/file_type_git.png");
	public static final Icon FONT = IconLoader.getIcon("/icons/file_type_font.png");
	public static final Icon HTML = IconLoader.getIcon("/icons/file_type_html.png");
	public static final Icon CSS = IconLoader.getIcon("/icons/file_type_css.png");
	public static final Icon XML = IconLoader.getIcon("/icons/file_type_markup.png");
	public static final Icon JSON = IconLoader.getIcon("/icons/file_type_settings.png");
	public static final Icon YAML = IconLoader.getIcon("/icons/file_type_yaml.png");
	public static final Icon GRUNT = IconLoader.getIcon("/icons/file_type_gruntfile.png");
	public static final Icon NPM = IconLoader.getIcon("/icons/file_type_npm.png");
	public static final Icon BINARY = IconLoader.getIcon("/icons/file_type_binary.png");
	public static final Icon TEXT = IconLoader.getIcon("/icons/file_type_text.png");
	public static final Icon IMAGE = IconLoader.getIcon("/icons/file_type_image.png");

	public AfterglowIcons()
	{}

	@Nullable
	public static final Icon getIcon(VirtualFile file, int flags, @Nullable Project project)
	{
		if(file.isDirectory())
			return DIRECTORY;

		String extension = file.getExtension();
		if(extension == null)
		{
			switch(file.getName())
			{
				case "Podfile":
					return RUBY;
			}

			return ANY;
		}

		switch(extension.toLowerCase())
		{
			case "h":
			case "hpp":
			case "pch":
				return HEADER;
			case "cpp":
				return CPPFILE;
			case "c":
				return CFILE;
			case "mm":
			case "m":
				return MFILE;

			case "gitignore":
			case "gitmodules":
				return GIT;

			case "dylib":
			case "tbd":
			case "storyboard":
			case "xib":
			case "xcdatamodel":
			case "xcdatamodeld":
				return BINARY;

			case "xcodeproj":
				return null;

			case "py":
				return PYTHON;
			case "rb":
				return RUBY;
			case "js":
				switch(file.getName())
				{
					case "Gruntfile.js":
						return GRUNT;
				}

				return JAVASCRIPT;

			case "md":
			case "markdown":
				return MARKDOWN;

			case "sh":
				return SHELL;

			case "txt":
			case "strings":
				// Special case
				if(PlatformUtils.isCLion())
				{
					switch(file.getName())
					{
						case "CMakeLists.txt":
							return null;
					}
				}

				return TEXT;

			case "cmake":
				return (PlatformUtils.isCLion()) ? null : TEXT;

			case "ttf":
			case "otf":
			case "ttc":
				return FONT;

			// Markup
			case "json":
				switch(file.getName())
				{
					case "package.json":
						return NPM;
				}

				return JSON;
			case "xml":
			case "plist":
				return XML;
			case "yml":
				return YAML;
			case "html":
			case "xhtml":
				return HTML;
			case "css":
				return CSS;


			case "png":
			case "jpg":
			case "tga":
			case "bmp":
			case "gif":
				return IMAGE;

			default:
				Language language = null;

				if(project != null)
				{
					PsiManager psiManager = PsiManager.getInstance(project);
					PsiFile psiFile = psiManager.findFile(file);

					if(psiFile != null)
						language = psiFile.getLanguage();
				}

				if(language != null)
				{
					switch(language.getID())
					{
						case "ObjectiveC":
							return CFILE;
						case "XML":
							return XML;
						case "JSON":
							return JSON;
					}
				}

				FileType type = file.getFileType();
				return type.isBinary() ? BINARY : ANY;
		}
	}
}